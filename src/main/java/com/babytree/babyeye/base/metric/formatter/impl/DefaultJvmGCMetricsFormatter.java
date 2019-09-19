package com.babytree.babyeye.base.metric.formatter.impl;

import com.babytree.babyeye.base.metric.JvmGCMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmGCMetricsFormatter;
import com.babytree.babyeye.base.util.DateFormatUtils;

import java.util.List;


public class DefaultJvmGCMetricsFormatter implements JvmGCMetricsFormatter {

    @Override
    public String format(List<JvmGCMetrics> metricsList, long startMillis, long stopMillis) {
        int[] statisticsArr = getStatistics(metricsList);
        int maxGCNameLength = statisticsArr[0];

        String dataTitleFormat = "%-" + maxGCNameLength + "s%9s%9s%n";
        StringBuilder sb = new StringBuilder((metricsList.size() + 2) * (9 * 3 + 64));
        sb.append("JVM GC Metrics [").append(DateFormatUtils.format(startMillis)).append(", ").append(DateFormatUtils.format(stopMillis)).append("]").append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "Name", "Count", "Time"));
        if (metricsList.isEmpty()) {
            return sb.toString();
        }

        String dataFormat = "%-" + maxGCNameLength + "s%9d%9d%n";
        for (int i = 0; i < metricsList.size(); ++i) {
            JvmGCMetrics metrics = metricsList.get(i);
            sb.append(String.format(dataFormat,
                    metrics.getGcName(),
                    metrics.getCollectCount(),
                    metrics.getCollectTime()));
        }
        return sb.toString();
    }

    /**
     * @param metricsList
     * @return : int[0]:max(api.length)
     */
    private int[] getStatistics(List<JvmGCMetrics> metricsList) {
        int[] result = {1};
        for (int i = 0; i < metricsList.size(); ++i) {
            JvmGCMetrics metrics = metricsList.get(i);
            if (metrics == null) {
                continue;
            }

            result[0] = Math.max(result[0], metrics.getGcName().length());
        }
        return result;
    }
}
