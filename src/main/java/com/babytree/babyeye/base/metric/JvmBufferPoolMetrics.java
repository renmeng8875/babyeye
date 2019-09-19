package com.babytree.babyeye.base.metric;
import java.lang.management.BufferPoolMXBean;


public class JvmBufferPoolMetrics extends Metrics {

    private static final long serialVersionUID = 1308517280962399791L;

    private String name;

    private long count;

    private long memoryUsed;

    private long memoryCapacity;

    public JvmBufferPoolMetrics(BufferPoolMXBean mxBean) {
        this.name = mxBean.getName();
        this.count = mxBean.getCount();
        this.memoryUsed = mxBean.getMemoryUsed();
        this.memoryCapacity = mxBean.getTotalCapacity();
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public long getMemoryUsed() {
        return memoryUsed;
    }

    public long getMemoryCapacity() {
        return memoryCapacity;
    }

    @Override
    public String toString() {
        return "JvmBufferPoolMetrics{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", memoryUsed=" + memoryUsed +
                ", memoryCapacity=" + memoryCapacity +
                '}';
    }
}
