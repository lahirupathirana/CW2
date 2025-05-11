package com.concert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ReservationClient {

    public static void main(String[] args) {
        // Connect to the server
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        // Create a blocking stub (synchronous calls)
        ReservationServiceGrpc.ReservationServiceBlockingStub client =
                ReservationServiceGrpc.newBlockingStub(channel);

        // Add a new show
        AddShowRequest addRequest = AddShowRequest.newBuilder()
                .setShowName("Coldplay Live")
                .setSeatCount(2)
                .build();

        AddShowResponse addResponse = client.addShow(addRequest);
        System.out.println("Add Show Response: " + addResponse.getStatus());

        // Reserve a seat
        ReserveRequest reserveRequest = ReserveRequest.newBuilder()
                .setShowName("Coldplay Live")
                .build();

        ReserveResponse reserveResponse1 = client.reserveSeat(reserveRequest);
        System.out.println("First reservation: " + reserveResponse1.getStatus());

        ReserveResponse reserveResponse2 = client.reserveSeat(reserveRequest);
        System.out.println("Second reservation: " + reserveResponse2.getStatus());

        ReserveResponse reserveResponse3 = client.reserveSeat(reserveRequest);
        System.out.println("Third reservation: " + reserveResponse3.getStatus());

        // Close the channel
        channel.shutdown();
    }
}
