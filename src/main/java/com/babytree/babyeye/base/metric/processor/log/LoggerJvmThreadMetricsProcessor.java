package com.babytree.babyeye.base.metric.processor.log;

import com.babytree.babyeye.base.metric.JvmThreadMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmThreadMetricsFormatter;
import com.babytree.babyeye.base.metric.formatter.impl.DefaultJvmThreadMetricsFormatter;
import com.babytree.babyeye.base.metric.processor.AbstractJvmThreadMetricsProcessor;
import com.babytree.babyeye.base.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class LoggerJvmThreadMetricsProcessor extends AbstractJvmThreadMetricsProcessor {

    private ConcurrentHashMap<Long, List<JvmThreadMetrics>> metricsMap = new ConcurrentHashMap<>(8);

    private JvmThreadMetricsFormatter metricsFormatter = new DefaultJvmThreadMetricsFormatter();

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        metricsMap.put(processId, new ArrayList<JvmThreadMetrics>(1));
    }

    @Override
    public void process(JvmThreadMetrics metrics, long processId, long startMillis, long stopMillis) {
        List<JvmThreadMetrics> metricsList = metricsMap.get(processId);
        if (metricsList != null) {
            metricsList.add(metrics);
        } else {
            Logger.error("LoggerJvmThreadMetricsProcessor.process(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        List<JvmThreadMetrics> metricsList = metricsMap.remove(processId);
        if (metricsList != null) {
            logger.logAndFlush(metricsFormatter.format(metricsList, startMillis, stopMillis));
        } else {
            Logger.error("LoggerJvmThreadMetricsProcessor.afterProcess(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }
}
