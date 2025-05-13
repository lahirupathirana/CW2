package com.concert;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: reservation.proto")
public final class ReservationServiceGrpc {

  private ReservationServiceGrpc() {}

  public static final String SERVICE_NAME = "ReservationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.concert.AddShowRequest,
      com.concert.AddShowResponse> getAddShowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddShow",
      requestType = com.concert.AddShowRequest.class,
      responseType = com.concert.AddShowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.AddShowRequest,
      com.concert.AddShowResponse> getAddShowMethod() {
    io.grpc.MethodDescriptor<com.concert.AddShowRequest, com.concert.AddShowResponse> getAddShowMethod;
    if ((getAddShowMethod = ReservationServiceGrpc.getAddShowMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getAddShowMethod = ReservationServiceGrpc.getAddShowMethod) == null) {
          ReservationServiceGrpc.getAddShowMethod = getAddShowMethod =
              io.grpc.MethodDescriptor.<com.concert.AddShowRequest, com.concert.AddShowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddShow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.AddShowRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.AddShowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("AddShow"))
              .build();
        }
      }
    }
    return getAddShowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.ReserveTicketRequest,
      com.concert.ReserveTicketResponse> getReserveTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveTicket",
      requestType = com.concert.ReserveTicketRequest.class,
      responseType = com.concert.ReserveTicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.ReserveTicketRequest,
      com.concert.ReserveTicketResponse> getReserveTicketMethod() {
    io.grpc.MethodDescriptor<com.concert.ReserveTicketRequest, com.concert.ReserveTicketResponse> getReserveTicketMethod;
    if ((getReserveTicketMethod = ReservationServiceGrpc.getReserveTicketMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getReserveTicketMethod = ReservationServiceGrpc.getReserveTicketMethod) == null) {
          ReservationServiceGrpc.getReserveTicketMethod = getReserveTicketMethod =
              io.grpc.MethodDescriptor.<com.concert.ReserveTicketRequest, com.concert.ReserveTicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.ReserveTicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.ReserveTicketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("ReserveTicket"))
              .build();
        }
      }
    }
    return getReserveTicketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.ShowStatusRequest,
      com.concert.ShowStatusResponse> getGetShowStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetShowStatus",
      requestType = com.concert.ShowStatusRequest.class,
      responseType = com.concert.ShowStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.ShowStatusRequest,
      com.concert.ShowStatusResponse> getGetShowStatusMethod() {
    io.grpc.MethodDescriptor<com.concert.ShowStatusRequest, com.concert.ShowStatusResponse> getGetShowStatusMethod;
    if ((getGetShowStatusMethod = ReservationServiceGrpc.getGetShowStatusMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getGetShowStatusMethod = ReservationServiceGrpc.getGetShowStatusMethod) == null) {
          ReservationServiceGrpc.getGetShowStatusMethod = getGetShowStatusMethod =
              io.grpc.MethodDescriptor.<com.concert.ShowStatusRequest, com.concert.ShowStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetShowStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.ShowStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.ShowStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("GetShowStatus"))
              .build();
        }
      }
    }
    return getGetShowStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.UpdateRequest,
      com.concert.UpdateResponse> getSyncUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SyncUpdate",
      requestType = com.concert.UpdateRequest.class,
      responseType = com.concert.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.UpdateRequest,
      com.concert.UpdateResponse> getSyncUpdateMethod() {
    io.grpc.MethodDescriptor<com.concert.UpdateRequest, com.concert.UpdateResponse> getSyncUpdateMethod;
    if ((getSyncUpdateMethod = ReservationServiceGrpc.getSyncUpdateMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getSyncUpdateMethod = ReservationServiceGrpc.getSyncUpdateMethod) == null) {
          ReservationServiceGrpc.getSyncUpdateMethod = getSyncUpdateMethod =
              io.grpc.MethodDescriptor.<com.concert.UpdateRequest, com.concert.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SyncUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("SyncUpdate"))
              .build();
        }
      }
    }
    return getSyncUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.SyncRequest,
      com.concert.SyncResponse> getSyncAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "syncAll",
      requestType = com.concert.SyncRequest.class,
      responseType = com.concert.SyncResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.SyncRequest,
      com.concert.SyncResponse> getSyncAllMethod() {
    io.grpc.MethodDescriptor<com.concert.SyncRequest, com.concert.SyncResponse> getSyncAllMethod;
    if ((getSyncAllMethod = ReservationServiceGrpc.getSyncAllMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getSyncAllMethod = ReservationServiceGrpc.getSyncAllMethod) == null) {
          ReservationServiceGrpc.getSyncAllMethod = getSyncAllMethod =
              io.grpc.MethodDescriptor.<com.concert.SyncRequest, com.concert.SyncResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "syncAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.SyncRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.SyncResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("syncAll"))
              .build();
        }
      }
    }
    return getSyncAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.PrepareRequest,
      com.concert.PrepareResponse> getPrepareMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Prepare",
      requestType = com.concert.PrepareRequest.class,
      responseType = com.concert.PrepareResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.PrepareRequest,
      com.concert.PrepareResponse> getPrepareMethod() {
    io.grpc.MethodDescriptor<com.concert.PrepareRequest, com.concert.PrepareResponse> getPrepareMethod;
    if ((getPrepareMethod = ReservationServiceGrpc.getPrepareMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getPrepareMethod = ReservationServiceGrpc.getPrepareMethod) == null) {
          ReservationServiceGrpc.getPrepareMethod = getPrepareMethod =
              io.grpc.MethodDescriptor.<com.concert.PrepareRequest, com.concert.PrepareResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Prepare"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.PrepareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.PrepareResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("Prepare"))
              .build();
        }
      }
    }
    return getPrepareMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.CommitRequest,
      com.concert.Ack> getCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Commit",
      requestType = com.concert.CommitRequest.class,
      responseType = com.concert.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.CommitRequest,
      com.concert.Ack> getCommitMethod() {
    io.grpc.MethodDescriptor<com.concert.CommitRequest, com.concert.Ack> getCommitMethod;
    if ((getCommitMethod = ReservationServiceGrpc.getCommitMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getCommitMethod = ReservationServiceGrpc.getCommitMethod) == null) {
          ReservationServiceGrpc.getCommitMethod = getCommitMethod =
              io.grpc.MethodDescriptor.<com.concert.CommitRequest, com.concert.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Commit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.CommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("Commit"))
              .build();
        }
      }
    }
    return getCommitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.concert.AbortRequest,
      com.concert.Ack> getAbortMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Abort",
      requestType = com.concert.AbortRequest.class,
      responseType = com.concert.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.concert.AbortRequest,
      com.concert.Ack> getAbortMethod() {
    io.grpc.MethodDescriptor<com.concert.AbortRequest, com.concert.Ack> getAbortMethod;
    if ((getAbortMethod = ReservationServiceGrpc.getAbortMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getAbortMethod = ReservationServiceGrpc.getAbortMethod) == null) {
          ReservationServiceGrpc.getAbortMethod = getAbortMethod =
              io.grpc.MethodDescriptor.<com.concert.AbortRequest, com.concert.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Abort"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.AbortRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.concert.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("Abort"))
              .build();
        }
      }
    }
    return getAbortMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReservationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReservationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReservationServiceStub>() {
        @java.lang.Override
        public ReservationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReservationServiceStub(channel, callOptions);
        }
      };
    return ReservationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReservationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReservationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReservationServiceBlockingStub>() {
        @java.lang.Override
        public ReservationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReservationServiceBlockingStub(channel, callOptions);
        }
      };
    return ReservationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReservationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReservationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReservationServiceFutureStub>() {
        @java.lang.Override
        public ReservationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReservationServiceFutureStub(channel, callOptions);
        }
      };
    return ReservationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ReservationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addShow(com.concert.AddShowRequest request,
        io.grpc.stub.StreamObserver<com.concert.AddShowResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddShowMethod(), responseObserver);
    }

    /**
     */
    public void reserveTicket(com.concert.ReserveTicketRequest request,
        io.grpc.stub.StreamObserver<com.concert.ReserveTicketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReserveTicketMethod(), responseObserver);
    }

    /**
     */
    public void getShowStatus(com.concert.ShowStatusRequest request,
        io.grpc.stub.StreamObserver<com.concert.ShowStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetShowStatusMethod(), responseObserver);
    }

    /**
     */
    public void syncUpdate(com.concert.UpdateRequest request,
        io.grpc.stub.StreamObserver<com.concert.UpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSyncUpdateMethod(), responseObserver);
    }

    /**
     */
    public void syncAll(com.concert.SyncRequest request,
        io.grpc.stub.StreamObserver<com.concert.SyncResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSyncAllMethod(), responseObserver);
    }

    /**
     */
    public void prepare(com.concert.PrepareRequest request,
        io.grpc.stub.StreamObserver<com.concert.PrepareResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrepareMethod(), responseObserver);
    }

    /**
     */
    public void commit(com.concert.CommitRequest request,
        io.grpc.stub.StreamObserver<com.concert.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCommitMethod(), responseObserver);
    }

    /**
     */
    public void abort(com.concert.AbortRequest request,
        io.grpc.stub.StreamObserver<com.concert.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAbortMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddShowMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.AddShowRequest,
                com.concert.AddShowResponse>(
                  this, METHODID_ADD_SHOW)))
          .addMethod(
            getReserveTicketMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.ReserveTicketRequest,
                com.concert.ReserveTicketResponse>(
                  this, METHODID_RESERVE_TICKET)))
          .addMethod(
            getGetShowStatusMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.ShowStatusRequest,
                com.concert.ShowStatusResponse>(
                  this, METHODID_GET_SHOW_STATUS)))
          .addMethod(
            getSyncUpdateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.UpdateRequest,
                com.concert.UpdateResponse>(
                  this, METHODID_SYNC_UPDATE)))
          .addMethod(
            getSyncAllMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.SyncRequest,
                com.concert.SyncResponse>(
                  this, METHODID_SYNC_ALL)))
          .addMethod(
            getPrepareMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.PrepareRequest,
                com.concert.PrepareResponse>(
                  this, METHODID_PREPARE)))
          .addMethod(
            getCommitMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.CommitRequest,
                com.concert.Ack>(
                  this, METHODID_COMMIT)))
          .addMethod(
            getAbortMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.concert.AbortRequest,
                com.concert.Ack>(
                  this, METHODID_ABORT)))
          .build();
    }
  }

  /**
   */
  public static final class ReservationServiceStub extends io.grpc.stub.AbstractAsyncStub<ReservationServiceStub> {
    private ReservationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReservationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReservationServiceStub(channel, callOptions);
    }

    /**
     */
    public void addShow(com.concert.AddShowRequest request,
        io.grpc.stub.StreamObserver<com.concert.AddShowResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddShowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reserveTicket(com.concert.ReserveTicketRequest request,
        io.grpc.stub.StreamObserver<com.concert.ReserveTicketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReserveTicketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getShowStatus(com.concert.ShowStatusRequest request,
        io.grpc.stub.StreamObserver<com.concert.ShowStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetShowStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void syncUpdate(com.concert.UpdateRequest request,
        io.grpc.stub.StreamObserver<com.concert.UpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSyncUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void syncAll(com.concert.SyncRequest request,
        io.grpc.stub.StreamObserver<com.concert.SyncResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSyncAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void prepare(com.concert.PrepareRequest request,
        io.grpc.stub.StreamObserver<com.concert.PrepareResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void commit(com.concert.CommitRequest request,
        io.grpc.stub.StreamObserver<com.concert.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void abort(com.concert.AbortRequest request,
        io.grpc.stub.StreamObserver<com.concert.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAbortMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReservationServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ReservationServiceBlockingStub> {
    private ReservationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReservationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReservationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.concert.AddShowResponse addShow(com.concert.AddShowRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddShowMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.ReserveTicketResponse reserveTicket(com.concert.ReserveTicketRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReserveTicketMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.ShowStatusResponse getShowStatus(com.concert.ShowStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetShowStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.UpdateResponse syncUpdate(com.concert.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSyncUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.SyncResponse syncAll(com.concert.SyncRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSyncAllMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.PrepareResponse prepare(com.concert.PrepareRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrepareMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.Ack commit(com.concert.CommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCommitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.concert.Ack abort(com.concert.AbortRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAbortMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReservationServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ReservationServiceFutureStub> {
    private ReservationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReservationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReservationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.AddShowResponse> addShow(
        com.concert.AddShowRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddShowMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.ReserveTicketResponse> reserveTicket(
        com.concert.ReserveTicketRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReserveTicketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.ShowStatusResponse> getShowStatus(
        com.concert.ShowStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetShowStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.UpdateResponse> syncUpdate(
        com.concert.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSyncUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.SyncResponse> syncAll(
        com.concert.SyncRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSyncAllMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.PrepareResponse> prepare(
        com.concert.PrepareRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.Ack> commit(
        com.concert.CommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.concert.Ack> abort(
        com.concert.AbortRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAbortMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_SHOW = 0;
  private static final int METHODID_RESERVE_TICKET = 1;
  private static final int METHODID_GET_SHOW_STATUS = 2;
  private static final int METHODID_SYNC_UPDATE = 3;
  private static final int METHODID_SYNC_ALL = 4;
  private static final int METHODID_PREPARE = 5;
  private static final int METHODID_COMMIT = 6;
  private static final int METHODID_ABORT = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReservationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReservationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_SHOW:
          serviceImpl.addShow((com.concert.AddShowRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.AddShowResponse>) responseObserver);
          break;
        case METHODID_RESERVE_TICKET:
          serviceImpl.reserveTicket((com.concert.ReserveTicketRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.ReserveTicketResponse>) responseObserver);
          break;
        case METHODID_GET_SHOW_STATUS:
          serviceImpl.getShowStatus((com.concert.ShowStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.ShowStatusResponse>) responseObserver);
          break;
        case METHODID_SYNC_UPDATE:
          serviceImpl.syncUpdate((com.concert.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.UpdateResponse>) responseObserver);
          break;
        case METHODID_SYNC_ALL:
          serviceImpl.syncAll((com.concert.SyncRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.SyncResponse>) responseObserver);
          break;
        case METHODID_PREPARE:
          serviceImpl.prepare((com.concert.PrepareRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.PrepareResponse>) responseObserver);
          break;
        case METHODID_COMMIT:
          serviceImpl.commit((com.concert.CommitRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.Ack>) responseObserver);
          break;
        case METHODID_ABORT:
          serviceImpl.abort((com.concert.AbortRequest) request,
              (io.grpc.stub.StreamObserver<com.concert.Ack>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReservationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReservationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.concert.ReservationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReservationService");
    }
  }

  private static final class ReservationServiceFileDescriptorSupplier
      extends ReservationServiceBaseDescriptorSupplier {
    ReservationServiceFileDescriptorSupplier() {}
  }

  private static final class ReservationServiceMethodDescriptorSupplier
      extends ReservationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReservationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ReservationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReservationServiceFileDescriptorSupplier())
              .addMethod(getAddShowMethod())
              .addMethod(getReserveTicketMethod())
              .addMethod(getGetShowStatusMethod())
              .addMethod(getSyncUpdateMethod())
              .addMethod(getSyncAllMethod())
              .addMethod(getPrepareMethod())
              .addMethod(getCommitMethod())
              .addMethod(getAbortMethod())
              .build();
        }
      }
    }
    return result;
  }
}
