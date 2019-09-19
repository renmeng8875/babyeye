package com.babytree.babyeye.base.metric.processor;

import com.babytree.babyeye.base.constant.PropertyValues;
import com.babytree.babyeye.base.metric.processor.discard.DiscardJvmThreadMetricsProcessor;
import com.babytree.babyeye.base.metric.processor.log.*;

public class MetricsProcessorFactory {

    public static JvmThreadMetricsProcessor getThreadMetricsProcessor(int processorType) {
        switch (processorType) {
            case PropertyValues.METRICS_PROCESS_TYPE_STDOUT:
            case PropertyValues.METRICS_PROCESS_TYPE_LOGGER:
                return new LoggerJvmThreadMetricsProcessor();

            default:
                return new DiscardJvmThreadMetricsProcessor();
        }
    }

    public static MethodMetricsProcessor getMethodMetricsProcessor(int processorType) {
        switch (processorType) {
            case PropertyValues.METRICS_PROCESS_TYPE_STDOUT:
            case PropertyValues.METRICS_PROCESS_TYPE_LOGGER:
                return new LoggerMethodMetricsProcessor();
            default:
                return new LoggerMethodMetricsProcessor();
        }
    }

}
