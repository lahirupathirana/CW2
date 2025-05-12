package com.concert;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;


import java.io.IOException;

public class ZooKeeperClient implements Watcher {

    private ZooKeeper zooKeeper;

    public ZooKeeperClient(String hostPort) throws IOException {
        this.zooKeeper = new ZooKeeper(hostPort, 3000, this);
    }

    public String createEphemeralSequential(String path, byte[] data) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public void watchChildren(String path) throws KeeperException, InterruptedException {
        zooKeeper.getChildren(path, true);
    }

    public Stat exists(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, true);
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    public ZooKeeper getZooKeeper() {
        return this.zooKeeper;
    }
 

    @Override
    public void process(WatchedEvent event) {
        System.out.println("ZooKeeper event received: " + event);
    }
}
