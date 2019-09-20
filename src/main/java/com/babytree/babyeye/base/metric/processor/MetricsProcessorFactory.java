package com.babytree.babyeye.base.metric.processor;

import com.babytree.babyeye.base.metric.processor.log.LoggerJvmThreadMetricsProcessor;
import com.babytree.babyeye.base.metric.processor.log.LoggerMethodMetricsProcessor;

public class MetricsProcessorFactory {

    public static JvmThreadMetricsProcessor getThreadMetricsProcessor() {
        return new LoggerJvmThreadMetricsProcessor();
    }

    public static MethodMetricsProcessor getMethodMetricsProcessor() {
        return new LoggerMethodMetricsProcessor();
    }

}
