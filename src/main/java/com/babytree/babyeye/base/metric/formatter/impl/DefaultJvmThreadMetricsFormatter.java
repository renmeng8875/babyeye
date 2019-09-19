package com.babytree.babyeye.base.metric.formatter.impl;

import com.babytree.babyeye.base.metric.JvmThreadMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmThreadMetricsFormatter;
import com.babytree.babyeye.base.util.DateFormatUtils;

import java.util.List;

public class DefaultJvmThreadMetricsFormatter implements JvmThreadMetricsFormatter {

    @Override
    public String format(List<JvmThreadMetrics> metricsList, long startMillis, long stopMillis) {
        String dataTitleFormat = "%-14s%14s%14s%14s%14s%14s%14s%14s%14s%14s%n";
        StringBuilder sb = new StringBuilder((metricsList.size() + 2) * (14 * 10 + 64));
        sb.append("MyPerf4J JVM Thread Metrics [").append(DateFormatUtils.format(startMillis)).append(", ").append(DateFormatUtils.format(stopMillis)).append("]").append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "TotalStarted", "Active", "Peak", "Daemon", "New", "Runnable", "Blocked", "Waiting", "TimedWaiting", "Terminated"));
        if (metricsList.isEmpty()) {
            return sb.toString();
        }

        String dataFormat = "%-14s%14d%14d%14d%14d%14d%14d%14d%14d%14d%n";
        for (int i = 0; i < metricsList.size(); ++i) {
            JvmThreadMetrics metrics = metricsList.get(i);
            sb.append(String.format(dataFormat,
                    metrics.getTotalStarted(),
                    metrics.getActive(),
                    metrics.getPeak(),
                    metrics.getDaemon(),
                    metrics.getNews(),
                    metrics.getRunnable(),
                    metrics.getBlocked(),
                    metrics.getWaiting(),
                    metrics.getTimedWaiting(),
                    metrics.getTerminated()
            ));
        }
        return sb.toString();
    }
}
