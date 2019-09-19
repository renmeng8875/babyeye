package com.babytree.babyeye.base.metric.processor.log;

import com.babytree.babyeye.base.metric.JvmBufferPoolMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmBufferPoolMetricsFormatter;
import com.babytree.babyeye.base.metric.formatter.impl.DefaultJvmBufferPoolMetricsFormatter;
import com.babytree.babyeye.base.metric.processor.AbstractJvmBufferPoolMetricsProcessor;
import com.babytree.babyeye.base.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class LoggerJvmBufferPoolMetricsProcessor extends AbstractJvmBufferPoolMetricsProcessor {

    private ConcurrentHashMap<Long, List<JvmBufferPoolMetrics>> metricsMap = new ConcurrentHashMap<>(8);

    private JvmBufferPoolMetricsFormatter metricsFormatter = new DefaultJvmBufferPoolMetricsFormatter();

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        metricsMap.put(processId, new ArrayList<JvmBufferPoolMetrics>(2));
    }

    @Override
    public void process(JvmBufferPoolMetrics metrics, long processId, long startMillis, long stopMillis) {
        List<JvmBufferPoolMetrics> metricsList = metricsMap.get(processId);
        if (metricsList != null) {
            metricsList.add(metrics);
        } else {
            Logger.error("LoggerJvmBufferPoolMetricsProcessor.process(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        List<JvmBufferPoolMetrics> metricsList = metricsMap.remove(processId);
        if (metricsList != null) {
            logger.logAndFlush(metricsFormatter.format(metricsList, startMillis, stopMillis));
        } else {
            Logger.error("LoggerJvmBufferPoolMetricsProcessor.afterProcess(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }
}
