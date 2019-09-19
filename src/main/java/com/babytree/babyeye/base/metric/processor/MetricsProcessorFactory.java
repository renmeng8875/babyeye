package com.babytree.babyeye.base.metric.processor;

import com.babytree.babyeye.base.constant.PropertyValues;
import com.babytree.babyeye.base.metric.processor.discard.DiscardJvmClassMetricsProcessor;
import com.babytree.babyeye.base.metric.processor.discard.DiscardJvmGCMetricsProcessor;
import com.babytree.babyeye.base.metric.processor.discard.DiscardJvmMemoryMetricsProcessor;
import com.babytree.babyeye.base.metric.processor.discard.DiscardJvmThreadMetricsProcessor;
import com.babytree.babyeye.base.metric.processor.log.*;

public class MetricsProcessorFactory {

    public static JvmClassMetricsProcessor getClassMetricsProcessor(int processorType) {
        switch (processorType) {
            case PropertyValues.METRICS_PROCESS_TYPE_STDOUT:
            case PropertyValues.METRICS_PROCESS_TYPE_LOGGER:
                return new LoggerJvmClassMetricsProcessor();
            default:
                return new DiscardJvmClassMetricsProcessor();
        }
    }

    public static JvmGCMetricsProcessor getGCMetricsProcessor(int processorType) {
        switch (processorType) {
            case PropertyValues.METRICS_PROCESS_TYPE_STDOUT:
            case PropertyValues.METRICS_PROCESS_TYPE_LOGGER:
                return new LoggerJvmGCMetricsProcessor();
            default:
                return new DiscardJvmGCMetricsProcessor();
        }
    }

    public static JvmMemoryMetricsProcessor getMemoryMetricsProcessor(int processorType) {
        switch (processorType) {
            case PropertyValues.METRICS_PROCESS_TYPE_STDOUT:
            case PropertyValues.METRICS_PROCESS_TYPE_LOGGER:
                return new LoggerJvmMemoryMetricsProcessor();

            default:
                return new DiscardJvmMemoryMetricsProcessor();
        }
    }

    public static JvmBufferPoolMetricsProcessor getBufferPoolMetricsProcessor(int processorType) {
        switch (processorType) {
            case PropertyValues.METRICS_PROCESS_TYPE_STDOUT:
            case PropertyValues.METRICS_PROCESS_TYPE_LOGGER:
                return new LoggerJvmBufferPoolMetricsProcessor();
            default:
                return new LoggerJvmBufferPoolMetricsProcessor();
        }
    }

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
