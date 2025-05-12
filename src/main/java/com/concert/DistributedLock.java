package com.concert;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class DistributedLock {

    private ZooKeeperClient zkClient;
    private String lockBasePath = "/locks";
    private String lockNodePath;

    public DistributedLock(String hostPort) throws IOException {
        this.zkClient = new ZooKeeperClient(hostPort);
    }

    public void acquireLock(String nodeId, int port) throws Exception {
        // Ensure /locks node exists
        if (zkClient.getZooKeeper().exists(lockBasePath, false) == null) {
            zkClient.getZooKeeper().create(
                    lockBasePath,
                    new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT
            );
        }

        // Create ephemeral sequential node
        String fullPath = zkClient.createEphemeralSequential(lockBasePath + "/lock_", nodeId.getBytes());
        this.lockNodePath = fullPath;

        System.out.println("🟡 Server starting as follower on port " + port + "...");

        new Thread(() -> {
            try {
                while (true) {
                    List<String> nodes = zkClient.getZooKeeper().getChildren(lockBasePath, false);
                    Collections.sort(nodes);
                    String smallestNode = nodes.get(0);
                    String myNode = fullPath.substring(fullPath.lastIndexOf('/') + 1);

                    if (smallestNode.equals(myNode)) {
                        if (!ReservationServiceImpl.isLeader) {
                            ReservationServiceImpl.isLeader = true;
                            System.out.println("🔺 Server on port " + port + " is now the LEADER");
                        }
                        break;
                    } else {
                        ReservationServiceImpl.isLeader = false;
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error in leader election thread: " + e.getMessage());
            }
        }).start();
    }

    public void releaseLock() throws Exception {
        if (lockNodePath != null) {
            zkClient.getZooKeeper().delete(lockNodePath, -1);
        }
        zkClient.close();
    }
}
