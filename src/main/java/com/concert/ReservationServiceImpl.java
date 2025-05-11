package com.concert;

import java.util.HashMap;
import java.util.Map;

import io.grpc.stub.StreamObserver;

public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    private final Map<String, Integer> shows = new HashMap<>();

    @Override
    public void addShow(AddShowRequest request, StreamObserver<AddShowResponse> responseObserver) {
        shows.put(request.getShowName(), request.getSeatCount());

        AddShowResponse response = AddShowResponse.newBuilder()
                .setStatus("Show added: " + request.getShowName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void reserveSeat(ReserveRequest request, StreamObserver<ReserveResponse> responseObserver) {
        String showName = request.getShowName();
        int seats = shows.getOrDefault(showName, 0);
        String status;

        if (seats > 0) {
            shows.put(showName, seats - 1);
            status = "Seat reserved!";
        } else {
            status = "No seats available.";
        }

        ReserveResponse response = ReserveResponse.newBuilder()
                .setStatus(status)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
