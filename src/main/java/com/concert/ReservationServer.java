package com.concert;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ReservationServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new ReservationServiceImpl())
                .build();

        server.start();
        System.out.println("Reservation gRPC server started on port 9090");
        server.awaitTermination();
    }
}
