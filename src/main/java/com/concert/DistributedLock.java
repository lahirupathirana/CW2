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

    public void acquireLock(String nodeId) throws Exception {
        // ✅ Step 1: Ensure /locks node exists
        if (zkClient.getZooKeeper().exists(lockBasePath, false) == null) {
            zkClient.getZooKeeper().create(
                    lockBasePath,
                    new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT
            );
            System.out.println("🛠️ Created ZooKeeper base path: " + lockBasePath);
        }

        // ✅ Step 2: Now create sequential ephemeral lock node under /locks
        String fullPath = zkClient.createEphemeralSequential(lockBasePath + "/lock_", nodeId.getBytes());
        this.lockNodePath = fullPath;
        System.out.println("🔐 Created ephemeral lock node: " + fullPath);

        // ✅ Step 3: Wait until we’re the smallest (leader)
        while (true) {
            List<String> nodes = zkClient.getZooKeeper().getChildren(lockBasePath, false);
            Collections.sort(nodes);

            String smallestNode = nodes.get(0);

            if ((lockBasePath + "/" + smallestNode).equals(fullPath)) {
                System.out.println("✅ " + nodeId + " acquired leadership!");
                break;
            } else {
                // System.out.println("⏳ " + nodeId + " waiting for leadership...");
                Thread.sleep(1000);
            }
        }
    }

    public void releaseLock() throws Exception {
        if (lockNodePath != null) {
            zkClient.getZooKeeper().delete(lockNodePath, -1);
        }
        zkClient.close();
    }
}
