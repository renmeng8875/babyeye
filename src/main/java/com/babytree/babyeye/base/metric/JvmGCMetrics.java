package com.babytree.babyeye.base.metric;

import java.lang.management.GarbageCollectorMXBean;


public class JvmGCMetrics extends Metrics {

    private static final long serialVersionUID = -233095689152915892L;

    private String gcName;

    private long collectCount;

    private long collectTime;

    public JvmGCMetrics(String gcName, long collectCount, long collectTime) {
        this.gcName = gcName;
        this.collectCount = collectCount;
        this.collectTime = collectTime;
    }

    public JvmGCMetrics(GarbageCollectorMXBean bean) {
        this(bean.getName(), bean.getCollectionCount(), bean.getCollectionTime());
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(long collectCount) {
        this.collectCount = collectCount;
    }

    public long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(long collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "JvmGCMetrics{" +
                "gcName='" + gcName + '\'' +
                ", collectCount=" + collectCount +
                ", collectTime=" + collectTime +
                "} " + super.toString();
    }

}
