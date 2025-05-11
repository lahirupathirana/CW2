package com.concert;

import io.grpc.stub.StreamObserver;
import java.util.HashMap;
import java.util.Map;

public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

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
        shows.put(request.getShowName(), new Show(request.getConcertSeats(), request.getAfterPartyTickets()));

        AddShowResponse response = AddShowResponse.newBuilder()
                .setStatus("Show added: " + request.getShowName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void reserveTicket(ReserveTicketRequest request, StreamObserver<ReserveTicketResponse> responseObserver) {
        Show show = shows.get(request.getShowName());

        String status;

        synchronized (this) {
            if (show == null) {
                status = "Show not found.";
            } else if (request.getIncludeAfterParty()) {
                if (show.concertSeats > 0 && show.afterPartyTickets > 0) {
                    show.concertSeats--;
                    show.afterPartyTickets--;
                    status = "Combo reserved: concert + after-party";
                } else {
                    status = "Booking failed: not enough tickets for combo.";
                }
            } else {
                if (show.concertSeats > 0) {
                    show.concertSeats--;
                    status = "Concert ticket reserved.";
                } else {
                    status = "Concert sold out.";
                }
            }
        }

        ReserveTicketResponse response = ReserveTicketResponse.newBuilder()
                .setStatus(status)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
