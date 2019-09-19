package com.babytree.babyeye.base.metric.formatter.impl;

import com.babytree.babyeye.base.metric.JvmBufferPoolMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmBufferPoolMetricsFormatter;
import com.babytree.babyeye.base.util.DateFormatUtils;

import java.util.List;

public class DefaultJvmBufferPoolMetricsFormatter implements JvmBufferPoolMetricsFormatter {

    @Override
    public String format(List<JvmBufferPoolMetrics> metricsList, long startMillis, long stopMillis) {
        String dataTitleFormat = "%-19s%19s%19s%19s%n";
        StringBuilder sb = new StringBuilder((metricsList.size() + 2) * (3 * 19 + 64));
        sb.append("MyPerf4J JVM BufferPool Metrics [").append(DateFormatUtils.format(startMillis)).append(", ").append(DateFormatUtils.format(stopMillis)).append("]").append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "Name", "Count", "MemoryUsed", "MemoryCapacity"));
        if (metricsList.isEmpty()) {
            return sb.toString();
        }

        String dataFormat = "%-19s%19d%19d%19d%n";
        for (int i = 0; i < metricsList.size(); ++i) {
            JvmBufferPoolMetrics metrics = metricsList.get(i);
            sb.append(String.format(dataFormat,
                    metrics.getName(),
                    metrics.getCount(),
                    metrics.getMemoryUsed(),
                    metrics.getMemoryCapacity()));
        }
        return sb.toString();
    }

}
