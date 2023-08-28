package com.sylvanas.others;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;

public class ConsistentHashingWithVirtualNode {
    /**
     * 集群地址列表
     */
    private static String[] groups = {
            "192.168.0.1:111",
            "192.168.0.2:111",
            "192.168.0.3:111",
            "192.168.0.4:111",
            "192.168.0.5:111"
    };

    /**
     * 真实集群列表
     */
    private static Set<String> realGroupsSet = new HashSet<>();

    /**
     * 虚拟节点映射关系
     */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private static final int VIRTUAL_NODE_NUM = 5000;

    static {
        // 先添加真实节点列表
        realGroupsSet.addAll(Arrays.asList(groups));

        // 将虚拟节点映射到Hash环上
        for (String realGroup : realGroupsSet) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String virtualNodeName = getVirtualNodeName(realGroup, i);
                int hash = HashUtil.fnv32Hash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }

    private static String getVirtualNodeName(String realName, int num) {
        return realName + "&&virtual-node-" + num;
    }

    private static String getRealNodeName(String virtualName) {
        return virtualName.split("&&")[0];
    }

    private static String getServer(String widgetKey) {
        int hash = HashUtil.fnv32Hash(widgetKey);
        // 只取出所有大于该hash值的部分而不必遍历整个Tree
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNodeName;
        if (subMap == null || subMap.isEmpty()) {
            // hash值在最尾部，应该映射到第一个group上
            virtualNodeName = virtualNodes.get(virtualNodes.firstKey());
        } else {
            virtualNodeName = subMap.get(subMap.firstKey());
        }
        return getRealNodeName(virtualNodeName);
    }

    private static void refreshHashCircle() {
        // 当集群变动时，刷新hash环，其余的集群在hash环上的位置不会发生变动
        virtualNodes.clear();
        for (String realGroup: realGroupsSet) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String virtualNodeName = getVirtualNodeName(realGroup, i);
                int hash = HashUtil.fnv32Hash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }
    private static void addGroup(String identifier) {
        realGroupsSet.add(identifier);
        refreshHashCircle();
    }

    private static void removeGroup(String identifier) {
        realGroupsSet.remove(identifier);
        refreshHashCircle();
    }

    public static void main(String[] args) {
        // 生成随机数进行测试
        Map<String, LongAdder> resMap = new HashMap<>();


        System.out.println("===============初始化分布==================");
        for (int weight = 0; weight < 100000; weight++) {
            String group = getServer(Integer.toString(weight));
            if (resMap.containsKey(group)) {
                resMap.get(group).increment();
            } else {
                resMap.put(group, new LongAdder());
            }
        }
        resMap.forEach((k, v) -> System.out.println("group " + k + ": " + v + "(" + v.longValue() / 100000.0D + "%)"));
        resMap.clear();

        System.out.println("===============添加节点==================");
        addGroup("192.168.0.6:111");
        for (int weight = 0; weight < 100000; weight++) {
            String group = getServer(Integer.toString(weight));
            if (resMap.containsKey(group)) {
                resMap.get(group).increment();
            } else {
                resMap.put(group, new LongAdder());
            }
        }
        resMap.forEach((k, v) -> System.out.println("group " + k + ": " + v + "(" + v.longValue() / 100000.0D + "%)"));
        resMap.clear();


        System.out.println("===============删除节点==================");
        removeGroup("192.168.0.5:111");
        for (int weight =
             0; weight < 100000; weight++) {
            String group = getServer(Integer.toString(weight));
            if (resMap.containsKey(group)) {
                resMap.get(group).increment();
            } else {
                resMap.put(group, new LongAdder());
            }
        }
        resMap.forEach((k, v) -> System.out.println("group " + k + ": " + v + "(" + v.longValue() / 100000.0D + "%)"));


    }
}