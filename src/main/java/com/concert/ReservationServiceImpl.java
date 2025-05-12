package com.concert;

import java.util.HashMap;
import java.util.Map;

import io.grpc.stub.StreamObserver;

public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    public static boolean isLeader = false;
    private final int currentPort;

    public ReservationServiceImpl(int port) {
        this.currentPort = port;
    }

    static class Show {

        int concertSeats;
        int afterPartyTickets;

        Show(int concertSeats, int afterPartyTickets) {
            this.concertSeats = concertSeats;
            this.afterPartyTickets = afterPartyTickets;
        }
    }

    private final Map<String, Show> shows = new HashMap<>();

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

        System.out.println("🎫 [Leader " + currentPort + "] added new event '"
                + request.getShowName() + "' with "
                + request.getConcertSeats() + " concert seats and "
                + request.getAfterPartyTickets() + " after-party tickets.");

        // ✅ Sync new show to all followers
        syncToFollowers(request.getShowName(),
                request.getConcertSeats(),
                request.getAfterPartyTickets());

        responseObserver.onNext(AddShowResponse.newBuilder()
                .setStatus("✅ Show added and synced by leader on port " + currentPort)
                .build());
        responseObserver.onCompleted();
    }

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
            status = "❌ Show not found.";
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

    private void syncToFollowers(String showName, int seats, int afters) {
        int[] followerPorts = {9091, 9092};
        for (int p : followerPorts) {
            if (p != currentPort) {
                System.out.println("➡️ Leader on port " + currentPort
                        + " sending update to follower on port " + p
                        + " [Show: " + showName + "]");
                new FollowerSyncClient("localhost", p)
                        .sendSync(showName, seats, afters, "leader-" + currentPort);
            }
        }
    }

    @Override
    public void syncUpdate(UpdateRequest request, StreamObserver<UpdateResponse> responseObserver) {
        synchronized (this) {
            shows.put(request.getShowName(), new Show(
                    request.getConcertSeats(),
                    request.getAfterPartyTickets()
            ));
        }

        String msg = "🟡 [Follower Port " + currentPort + "] updated show '"
                + request.getShowName() + "' from leader " + request.getSource()
                + " => Seats: " + request.getConcertSeats()
                + ", AfterParty: " + request.getAfterPartyTickets();

        System.out.println("==============================================");
        System.out.println(msg);
        System.out.println("==============================================");

        responseObserver.onNext(UpdateResponse.newBuilder()
                .setStatus("Follower " + currentPort + " successfully updated.")
                .build());
        responseObserver.onCompleted();
    }

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
