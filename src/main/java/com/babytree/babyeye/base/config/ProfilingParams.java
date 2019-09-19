package com.babytree.babyeye.base.config;


public class ProfilingParams {

    //单位:ms
    private int mostTimeThreshold;

    private int outThresholdCount;

    private ProfilingParams(int mostTimeThreshold, int outThresholdCount) {
        this.mostTimeThreshold = mostTimeThreshold;
        this.outThresholdCount = outThresholdCount;
    }

    public int getMostTimeThreshold() {
        return mostTimeThreshold;
    }

    public void setMostTimeThreshold(int mostTimeThreshold) {
        this.mostTimeThreshold = mostTimeThreshold;
    }

    public int getOutThresholdCount() {
        return outThresholdCount;
    }

    public void setOutThresholdCount(int outThresholdCount) {
        this.outThresholdCount = outThresholdCount;
    }

    public static ProfilingParams of(int mostTimeThreshold, int outThresholdCount) {
        return new ProfilingParams(mostTimeThreshold, outThresholdCount);
    }
}
