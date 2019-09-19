package com.babytree.babyeye.base.metric.formatter.impl;

import com.babytree.babyeye.base.metric.JvmClassMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmClassMetricsFormatter;
import com.babytree.babyeye.base.util.DateFormatUtils;

import java.util.List;

public final class DefaultJvmClassMetricsFormatter implements JvmClassMetricsFormatter {

    @Override
    public String format(List<JvmClassMetrics> metricsList, long startMillis, long stopMillis) {
        String dataTitleFormat = "%-10s%10s%10s%n";
        StringBuilder sb = new StringBuilder((metricsList.size() + 2) * (12 * 3 + 64));
        sb.append("MyPerf4J JVM Class Metrics [").append(DateFormatUtils.format(startMillis)).append(", ").append(DateFormatUtils.format(stopMillis)).append("]").append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "Total", "Loaded", "Unloaded"));
        if (metricsList.isEmpty()) {
            return sb.toString();
        }

        String dataFormat = "%-10d%10d%10d%n";
        for (int i = 0; i < metricsList.size(); ++i) {
            JvmClassMetrics metrics = metricsList.get(i);
            sb.append(String.format(dataFormat,
                    metrics.getTotal(),
                    metrics.getLoaded(),
                    metrics.getUnloaded()));
        }
        return sb.toString();
    }

}
