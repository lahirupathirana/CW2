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
        // boolean isLeader = lock.acquireLock();
        // Start gRPC server
        int wait = 0;
        while (!ReservationServiceImpl.isLeader && wait < 5) {
            Thread.sleep(500);
            wait++;
        }
        ReservationServiceImpl service = new ReservationServiceImpl(port);
        Server server = ServerBuilder
                .forPort(port)
                .addService(service)
                .build();

        server.start();
        System.out.println("Reservation gRPC server active on port " + port);
        // ReservationServiceImpl service = new ReservationServiceImpl(port, isLeader);
        if (!ReservationServiceImpl.isLeader) {
            System.out.println("Fallower as on port " + port);
            service.syncFromLeader();
        }else {
            System.out.println("Leader elected on port " + port);
        }
        server.awaitTermination();
    }
}
