package com.concert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class BuyTicketClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter show name to reserve:");
        String showName = scanner.nextLine();

        System.out.println("Do you want after-party ticket? (Y/N):");
        String includeAfter = scanner.nextLine().trim().toLowerCase();

        boolean includeAfterParty = includeAfter.equals("y");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        ReservationServiceGrpc.ReservationServiceBlockingStub client =
                ReservationServiceGrpc.newBlockingStub(channel);

        // Call check availability logic
        ShowStatusResponse statusResponse = client.getShowStatus(
                ShowStatusRequest.newBuilder().setShowName(showName).build());

        int remainingConcert = statusResponse.getConcertSeats();
        int remainingAfterParty = statusResponse.getAfterPartyTickets();

        System.out.println("Available: " + remainingConcert + " concert, " + remainingAfterParty + " after-party");

        if (remainingConcert < 3 && remainingAfterParty > 0) {
            System.out.println("⚠️  Less than 3 tickets left. Combo booking is mandatory.");
            includeAfterParty = true;
        }

        ReserveTicketRequest request = ReserveTicketRequest.newBuilder()
                .setShowName(showName)
                .setIncludeAfterParty(includeAfterParty)
                .build();

        ReserveTicketResponse response = client.reserveTicket(request);
        System.out.println("Booking status: " + response.getStatus());

        channel.shutdown();
    }
}
