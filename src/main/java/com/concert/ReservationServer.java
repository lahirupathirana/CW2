package com.concert;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ReservationServer {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("❗ Usage: java ReservationServer <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("🟢 Starting ReservationServer on port " + port);

        // Connect to ZooKeeper and run leader election
        DistributedLock lock = new DistributedLock("localhost:2181");
        lock.acquireLock("Node-" + port, port);  // port passed for logging

        // Start gRPC server
        ReservationServiceImpl service = new ReservationServiceImpl(port);
        Server server = ServerBuilder
                .forPort(port)
                .addService(service)
                .build();

        server.start();
        System.out.println("✅ Reservation gRPC server active on port " + port);
        server.awaitTermination();
    }
}
