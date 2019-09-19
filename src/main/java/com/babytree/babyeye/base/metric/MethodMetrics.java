package com.babytree.babyeye.base.metric;

import com.babytree.babyeye.base.MethodTag;

public class MethodMetrics extends Metrics {

    private static final long serialVersionUID = 7715008537772415621L;

    private static final int TP_50_IDX = 0;
    private static final int TP_90_IDX = 1;
    private static final int TP_95_IDX = 2;
    private static final int TP_99_IDX = 3;


    private static final double[] TOP_PERCENTILE_ARR = {0.5D, 0.9D, 0.95D, 0.99D};//注意：tpArr和TOP_PERCENTILE_ARR的大小请保持一致！！！！

    private final int[] tpArr = {-1, -1, -1, -1};//注意：tpArr和TOP_PERCENTILE_ARR的大小请保持一致！！！！

    private MethodTag methodTag;

    private int minTime = -1;//ms

    private double avgTime = -1.0F;//ms

    private int maxTime = -1;//ms

    private double stdDev = 0.0D;//Standard Deviation

    private int totalCount = -1;//ms

    private long startMillTime = -1L;

    private long stopMillTime = -1L;

    private MethodMetrics(MethodTag methodTag) {
        this.methodTag = methodTag;
    }

    public MethodTag getMethodTag() {
        return methodTag;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(double avgTime) {
        this.avgTime = avgTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public double getStdDev() {
        return stdDev;
    }

    public void setStdDev(double stdDev) {
        this.stdDev = stdDev;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTP50() {
        return tpArr[TP_50_IDX];
    }

    public void setTP50(int TP50) {
        this.tpArr[TP_50_IDX] = TP50;
    }

    public int getTP90() {
        return tpArr[TP_90_IDX];
    }

    public void setTP90(int TP90) {
        this.tpArr[TP_90_IDX] = TP90;
    }

    public int getTP95() {
        return tpArr[TP_95_IDX];
    }

    public void setTP95(int TP95) {
        this.tpArr[TP_95_IDX] = TP95;
    }

    public int getTP99() {
        return tpArr[TP_99_IDX];
    }

    public void setTP99(int TP99) {
        this.tpArr[TP_99_IDX] = TP99;
    }



    public int[] getTpArr() {
        return tpArr;
    }

    public long getStartMillTime() {
        return startMillTime;
    }

    public void setStartMillTime(long startMillTime) {
        this.startMillTime = startMillTime;
    }

    public long getStopMillTime() {
        return stopMillTime;
    }

    public void setStopMillTime(long stopMillTime) {
        this.stopMillTime = stopMillTime;
    }

    public int getRPS() {
        long seconds = (stopMillTime - startMillTime) / 1000;
        seconds = seconds <= 0L ? 1 : seconds;
        return (int) (totalCount / seconds);
    }

    @Override
    public String toString() {
        return "MethodMetrics{" +
                "methodTag=" + methodTag +
                ", RPS=" + getRPS() +
                ", TP50=" + getTP50() +
                ", TP90=" + getTP90() +
                ", TP95=" + getTP95() +
                ", TP99=" + getTP99() +
                ", minTime=" + minTime +
                ", avgTime=" + avgTime +
                ", maxTime=" + maxTime +
                ", stdDev=" + stdDev +
                ", totalCount=" + totalCount +
                '}';
    }

    public static MethodMetrics getInstance(MethodTag methodTag) {
        return new MethodMetrics(methodTag);
    }

    public static MethodMetrics getInstance(MethodTag methodTag, long startMillTime, long stopMillTime) {
        MethodMetrics result = new MethodMetrics(methodTag);
        result.setStartMillTime(startMillTime);
        result.setStopMillTime(stopMillTime);
        return result;
    }

    public static double[] getPercentiles() {
        return TOP_PERCENTILE_ARR;
    }

}
