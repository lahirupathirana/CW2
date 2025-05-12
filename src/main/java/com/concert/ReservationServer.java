package com.concert;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ReservationServer {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new ReservationServiceImpl())
                .build();
        DistributedLock lock = new DistributedLock("localhost:2181");
        lock.acquireLock("Node-" + port); // Example: Node-9090F
        server.start();
        System.out.println("Reservation gRPC server started on port " + port);
        ReservationServiceImpl.isLeader = true;
        server.awaitTermination();
    }
}
