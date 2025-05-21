package com.concert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoordinatorClient {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java CoordinatorClient <showName> <ticketCount> <afterParty true|false>");
            return;
        }

        String showName = args[0];
        int ticketCount = Integer.parseInt(args[1]);
        boolean needAfterParty = Boolean.parseBoolean(args[2]);

        String transactionId = UUID.randomUUID().toString();

        int[] participantPorts = {9090, 9091, 9092};

        List<ManagedChannel> channels = new ArrayList<>();
        List<ReservationServiceGrpc.ReservationServiceBlockingStub> stubs = new ArrayList<>();

        try {
            // Setup connections
            for (int port : participantPorts) {
                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                        .usePlaintext().build();
                channels.add(channel);
                stubs.add(ReservationServiceGrpc.newBlockingStub(channel));
            }

            // === Phase 1: PREPARE ===
            boolean allCommit = true;
            for (ReservationServiceGrpc.ReservationServiceBlockingStub stub : stubs) {
                PrepareResponse response = stub.prepare(PrepareRequest.newBuilder()
                        .setTransactionId(transactionId)
                        .setShowName(showName)
                        .setTicketCount(ticketCount)
                        .setNeedAfterParty(needAfterParty)
                        .build());

                if (!response.getVoteCommit()) {
                    allCommit = false;
                    System.out.println("One server voted ABORT");
                    break;
                }
            }

            // === Phase 2: COMMIT or ABORT ===
            if (allCommit) {
                System.out.println("All servers voted COMMIT. Sending COMMIT...");
                for (ReservationServiceGrpc.ReservationServiceBlockingStub stub : stubs) {
                    stub.commit(CommitRequest.newBuilder()
                            .setTransactionId(transactionId)
                            .setShowName(showName)
                            .build());
                }
            } else {
                System.out.println("Aborting transaction...");
                for (ReservationServiceGrpc.ReservationServiceBlockingStub stub : stubs) {
                    stub.abort(AbortRequest.newBuilder()
                            .setTransactionId(transactionId)
                            .setShowName(showName)
                            .build());
                }
            }

        } catch (Exception e) {
            System.err.println(" Error during transaction: " + e.getMessage());
        } finally {
            for (ManagedChannel ch : channels) {
                ch.shutdown();
            }
        }
    }
}
