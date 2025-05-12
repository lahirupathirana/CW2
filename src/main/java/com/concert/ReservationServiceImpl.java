package com.concert;

import io.grpc.stub.StreamObserver;
import java.util.HashMap;
import java.util.Map;

public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    public static boolean isLeader = false;  // Updated by DistributedLock
    private final int currentPort;

    // Constructor receives port from ReservationServer
    public ReservationServiceImpl(int port) {
        this.currentPort = port;
    }

    // Inner class to hold ticket info
    static class Show {
        int concertSeats;
        int afterPartyTickets;

        Show(int concertSeats, int afterPartyTickets) {
            this.concertSeats = concertSeats;
            this.afterPartyTickets = afterPartyTickets;
        }
    }

    private final Map<String, Show> shows = new HashMap<>();

    // Client -> Add a show
    @Override
    public void addShow(AddShowRequest request, StreamObserver<AddShowResponse> responseObserver) {
        if (!isLeader) {
            responseObserver.onNext(AddShowResponse.newBuilder()
                    .setStatus("❌ Not the leader. Cannot add shows.")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        shows.put(request.getShowName(), new Show(
                request.getConcertSeats(),
                request.getAfterPartyTickets()
        ));

        responseObserver.onNext(AddShowResponse.newBuilder()
                .setStatus("✅ Show added by leader on port " + currentPort)
                .build());
        responseObserver.onCompleted();
    }

    // Client -> Reserve a ticket
    @Override
    public void reserveTicket(ReserveTicketRequest request, StreamObserver<ReserveTicketResponse> responseObserver) {
        if (!isLeader) {
            responseObserver.onNext(ReserveTicketResponse.newBuilder()
                    .setStatus("❌ Not the leader. Cannot reserve tickets.")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        String status;
        Show show = shows.get(request.getShowName());

        if (show == null) {
            status = "Show not found.";
        } else {
            synchronized (this) {
                if (request.getIncludeAfterParty()) {
                    if (show.concertSeats > 0 && show.afterPartyTickets > 0) {
                        show.concertSeats--;
                        show.afterPartyTickets--;
                        status = "✅ Combo reserved";

                        syncToFollowers(request.getShowName(), show.concertSeats, show.afterPartyTickets);
                    } else {
                        status = "❌ Not enough combo tickets";
                    }
                } else {
                    if (show.concertSeats > 0) {
                        show.concertSeats--;
                        status = "✅ Concert ticket reserved";

                        syncToFollowers(request.getShowName(), show.concertSeats, show.afterPartyTickets);
                    } else {
                        status = "❌ Concert sold out";
                    }
                }
            }
        }

        responseObserver.onNext(ReserveTicketResponse.newBuilder()
                .setStatus(status)
                .build());
        responseObserver.onCompleted();
    }

    // Internal: Sync update to all known follower ports
    private void syncToFollowers(String showName, int seats, int afters) {
        int[] followerPorts = {9091, 9092};
        for (int p : followerPorts) {
            if (p != currentPort) {
                new FollowerSyncClient("localhost", p)
                        .sendSync(showName, seats, afters, "leader-" + currentPort);
            }
        }
    }

    // Follower -> Apply update from leader
    @Override
    public void syncUpdate(UpdateRequest request, StreamObserver<UpdateResponse> responseObserver) {
        shows.put(request.getShowName(), new Show(
                request.getConcertSeats(),
                request.getAfterPartyTickets()
        ));

        String msg = "🟡 Follower " + currentPort + " updated by leader: " + request.getSource();
        System.out.println(msg);

        responseObserver.onNext(UpdateResponse.newBuilder().setStatus(msg).build());
        responseObserver.onCompleted();
    }

    // Client -> Get show ticket counts
    @Override
    public void getShowStatus(ShowStatusRequest request, StreamObserver<ShowStatusResponse> responseObserver) {
        Show show = shows.get(request.getShowName());
        int concert = 0, afterParty = 0;

        if (show != null) {
            concert = show.concertSeats;
            afterParty = show.afterPartyTickets;
        }

        responseObserver.onNext(ShowStatusResponse.newBuilder()
                .setConcertSeats(concert)
                .setAfterPartyTickets(afterParty)
                .build());
        responseObserver.onCompleted();
    }
}
