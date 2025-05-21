package com.concert;

import java.util.HashMap;
import java.util.Map;

import io.grpc.stub.StreamObserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    public static boolean isLeader = false;
    private final int currentPort;
    private final Map<String, PendingReservation> pending = new HashMap<>();

    static class PendingReservation {

        String showName;
        int ticketCount;
        boolean withAfterParty;

        PendingReservation(String showName, int count, boolean party) {
            this.showName = showName;
            this.ticketCount = count;
            this.withAfterParty = party;
        }
    }

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

    @Override
    public void syncAll(SyncRequest request, StreamObserver<SyncResponse> responseObserver) {
        SyncResponse.Builder builder = SyncResponse.newBuilder();

        for (Map.Entry<String, Show> entry : shows.entrySet()) {
            builder.addShows(
                    ShowData.newBuilder()
                            .setShowName(entry.getKey())
                            .setConcertSeats(entry.getValue().concertSeats)
                            .setAfterPartyTickets(entry.getValue().afterPartyTickets)
                            .build()
            );
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    public void syncFromLeader() {
        if (!isLeader) {
            try {
                System.out.println("Syncing from leader...");
                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                        .usePlaintext().build();

                ReservationServiceGrpc.ReservationServiceBlockingStub stub
                        = ReservationServiceGrpc.newBlockingStub(channel);

                SyncRequest request = SyncRequest.newBuilder()
                        .setRequestor("Follower-" + currentPort).build();

                SyncResponse response = stub.syncAll(request);

                for (ShowData show : response.getShowsList()) {
                    shows.put(show.getShowName(),
                            new Show(show.getConcertSeats(), show.getAfterPartyTickets()));
                    System.out.println(" Synced: " + show.getShowName());
                }

                channel.shutdown();
            } catch (Exception e) {
                System.err.println("Failed to sync: " + e.getMessage());
            }
        }
    }

    @Override
    public void prepare(PrepareRequest request, StreamObserver<PrepareResponse> responseObserver) {
        Show show = shows.get(request.getShowName());

        boolean canCommit = false;
        if (show != null && show.concertSeats >= request.getTicketCount()) {
            if (!request.getNeedAfterParty() || show.afterPartyTickets >= request.getTicketCount()) {
                canCommit = true;
                pending.put(request.getTransactionId(), new PendingReservation(
                        request.getShowName(), request.getTicketCount(), request.getNeedAfterParty()
                ));
            }
        }

        responseObserver.onNext(PrepareResponse.newBuilder().setVoteCommit(canCommit).build());
        responseObserver.onCompleted();
    }

    @Override
    public void commit(CommitRequest request, StreamObserver<Ack> responseObserver) {
        PendingReservation res = pending.remove(request.getTransactionId());
        if (res != null) {
            Show show = shows.get(res.showName);
            show.concertSeats -= res.ticketCount;
            if (res.withAfterParty) {
                show.afterPartyTickets -= res.ticketCount;
            }
            System.out.println(" Commit completed on " + currentPort + " for " + res.showName);
        }

        responseObserver.onNext(Ack.newBuilder().setStatus("committed").build());
        responseObserver.onCompleted();
    }
    @Override
public void abort(AbortRequest request, StreamObserver<Ack> responseObserver) {
    pending.remove(request.getTransactionId()); // discard
    System.out.println(" Abort received for txn " + request.getTransactionId());
    responseObserver.onNext(Ack.newBuilder().setStatus("aborted").build());
    responseObserver.onCompleted();
}

}
