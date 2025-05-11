package com.concert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ReservationClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        ReservationServiceGrpc.ReservationServiceBlockingStub client =
                ReservationServiceGrpc.newBlockingStub(channel);

        AddShowRequest addRequest = AddShowRequest.newBuilder()
                .setShowName("CW2 Gala Night")
                .setConcertSeats(2)
                .setAfterPartyTickets(1)
                .build();

        AddShowResponse addResponse = client.addShow(addRequest);
        System.out.println("Add Show: " + addResponse.getStatus());

        ReserveTicketRequest comboRequest = ReserveTicketRequest.newBuilder()
                .setShowName("CW2 Gala Night")
                .setIncludeAfterParty(true)
                .build();

        ReserveTicketResponse combo1 = client.reserveTicket(comboRequest);
        System.out.println("Combo #1: " + combo1.getStatus());

        ReserveTicketRequest concertOnlyRequest = ReserveTicketRequest.newBuilder()
                .setShowName("CW2 Gala Night")
                .setIncludeAfterParty(false)
                .build();

        ReserveTicketResponse concertOnly = client.reserveTicket(concertOnlyRequest);
        System.out.println("Concert only: " + concertOnly.getStatus());

        ReserveTicketResponse combo2 = client.reserveTicket(comboRequest);
        System.out.println("Combo #2: " + combo2.getStatus());

        channel.shutdown();
    }
}
