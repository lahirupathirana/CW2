package com.concert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class FollowerSyncClient {

    private final String host;
    private final int port;

    public FollowerSyncClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendSync(String showName, int seats, int afters, String source) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        ReservationServiceGrpc.ReservationServiceBlockingStub stub = ReservationServiceGrpc.newBlockingStub(channel);

        UpdateRequest request = UpdateRequest.newBuilder()
                .setShowName(showName)
                .setConcertSeats(seats)
                .setAfterPartyTickets(afters)
                .setSource(source)
                .build();

        try {
            UpdateResponse response = stub.syncUpdate(request);
            System.out.println("Follower " + port + " sync result: " + response.getStatus());
        } catch (Exception e) {
            System.out.println("Could not sync with follower " + port + ": " + e.getMessage());
        }

        channel.shutdown();
    }
}
