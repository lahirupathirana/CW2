package com.concert;

import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EventStatusClient {

    public static void main(String[] args) {
        // Simplified round-robin fallback
        int[] followerPorts = {9091, 9092};
        boolean success = false;

        for (int port : followerPorts) {
            try {
                System.out.println("🌐 Connecting to follower at port " + port);
                ManagedChannel channel = ManagedChannelBuilder
                        .forAddress("localhost", port)
                        .usePlaintext()
                        .build();
                 System.out.println("🌐 Connected to follower at port " + port);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter event name to view status (or type 'exit'):");
                String input = scanner.nextLine();

                while (!input.equalsIgnoreCase("exit")) {
                    ReservationServiceGrpc.ReservationServiceBlockingStub stub =
                            ReservationServiceGrpc.newBlockingStub(channel);

                    ShowStatusRequest request = ShowStatusRequest.newBuilder()
                            .setShowName(input)
                            .build();

                    ShowStatusResponse response = stub.getShowStatus(request);

                    System.out.println("📊 Event: " + input);
                    System.out.println("🎟  Concert Tickets: " + response.getConcertSeats());
                    System.out.println("🎉 After Party Tickets: " + response.getAfterPartyTickets());

                    System.out.println("Enter another event name to check or 'exit':");
                    input = scanner.nextLine();
                }

                channel.shutdown();
                success = true;
                break;
            } catch (Exception e) {
                System.out.println("❌ Follower at port " + port + " not responding. Trying next...");
            }
        }

        if (!success) {
            System.out.println("❌ Could not connect to any follower server.");
        }
    }
}
