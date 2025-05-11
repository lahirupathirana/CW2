package com.concert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class AddShowClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from user
        System.out.println("Enter show name:");
        String showName = scanner.nextLine();

        System.out.println("Enter number of concert tickets:");
        int concertTickets = scanner.nextInt();

        System.out.println("Enter number of after-party tickets:");
        int afterPartyTickets = scanner.nextInt();

        // gRPC setup
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        ReservationServiceGrpc.ReservationServiceBlockingStub client =
                ReservationServiceGrpc.newBlockingStub(channel);

        AddShowRequest request = AddShowRequest.newBuilder()
                .setShowName(showName)
                .setConcertSeats(concertTickets)
                .setAfterPartyTickets(afterPartyTickets)
                .build();

        AddShowResponse response = client.addShow(request);
        System.out.println("Server response: " + response.getStatus());

        channel.shutdown();
    }
}
