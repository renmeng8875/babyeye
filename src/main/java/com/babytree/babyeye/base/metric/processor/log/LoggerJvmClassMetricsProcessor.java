package com.babytree.babyeye.base.metric.processor.log;

import com.babytree.babyeye.base.metric.JvmClassMetrics;
import com.babytree.babyeye.base.metric.formatter.JvmClassMetricsFormatter;
import com.babytree.babyeye.base.metric.formatter.impl.DefaultJvmClassMetricsFormatter;
import com.babytree.babyeye.base.metric.processor.AbstractJvmClassMetricsProcessor;
import com.babytree.babyeye.base.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerJvmClassMetricsProcessor extends AbstractJvmClassMetricsProcessor {

    private ConcurrentHashMap<Long, List<JvmClassMetrics>> metricsMap = new ConcurrentHashMap<>(8);

    private JvmClassMetricsFormatter metricsFormatter = new DefaultJvmClassMetricsFormatter();

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        metricsMap.put(processId, new ArrayList<JvmClassMetrics>(1));
    }

    @Override
    public void process(JvmClassMetrics metrics, long processId, long startMillis, long stopMillis) {
        List<JvmClassMetrics> metricsList = metricsMap.get(processId);
        if (metricsList != null) {
            metricsList.add(metrics);
        } else {
            Logger.error("LoggerJvmClassMetricsProcessor.process(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        List<JvmClassMetrics> metricsList = metricsMap.remove(processId);
        if (metricsList != null) {
            logger.logAndFlush(metricsFormatter.format(metricsList, startMillis, stopMillis));
        } else {
            Logger.error("LoggerJvmClassMetricsProcessor.afterProcess(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }
}
