package com.concert;

import java.util.HashMap;
import java.util.Map;

import io.grpc.stub.StreamObserver;

public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    // Shared static flag to indicate leader status
    public static boolean isLeader = false;

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
                    .setStatus("❌ This server is not the leader. Cannot add shows.")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        shows.put(request.getShowName(), new Show(
                request.getConcertSeats(),
                request.getAfterPartyTickets()
        ));

        responseObserver.onNext(AddShowResponse.newBuilder()
                .setStatus("✅ Show added by leader.")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void reserveTicket(ReserveTicketRequest request, StreamObserver<ReserveTicketResponse> responseObserver) {
        if (!isLeader) {
            responseObserver.onNext(ReserveTicketResponse.newBuilder()
                    .setStatus("❌ This server is not the leader. Cannot process reservations.")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        Show show = shows.get(request.getShowName());

        String status;

        synchronized (this) {
            if (show == null) {
                status = "Show not found.";
            } else if (request.getIncludeAfterParty()) {
                if (show.concertSeats > 0 && show.afterPartyTickets > 0) {
                    show.concertSeats--;
                    show.afterPartyTickets--;
                    status = "✅ Combo reserved (concert + after-party)";
                } else {
                    status = "❌ Not enough tickets for combo.";
                }
            } else {
                if (show.concertSeats > 0) {
                    show.concertSeats--;
                    status = "✅ Concert ticket reserved.";
                } else {
                    status = "❌ Concert sold out.";
                }
            }
        }

        responseObserver.onNext(ReserveTicketResponse.newBuilder()
                .setStatus(status)
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
