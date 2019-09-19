package com.babytree.core;


import com.babytree.babyeye.base.Scheduler;
import com.babytree.babyeye.base.config.ProfilingConfig;
import com.babytree.babyeye.base.constant.PropertyValues;
import com.babytree.babyeye.base.metric.processor.*;
import com.babytree.babyeye.core.scheduler.JvmMetricsScheduler;
import org.junit.Test;

public class JvmMetricsSchedulerTest {

    @Test
    public void test() {
        init();

        int processorType = PropertyValues.METRICS_PROCESS_TYPE_STDOUT;
        JvmClassMetricsProcessor classProcessor = MetricsProcessorFactory.getClassMetricsProcessor(processorType);
        JvmGCMetricsProcessor gcProcessor = MetricsProcessorFactory.getGCMetricsProcessor(processorType);
        JvmMemoryMetricsProcessor memoryProcessor = MetricsProcessorFactory.getMemoryMetricsProcessor(processorType);
        JvmThreadMetricsProcessor threadProcessor = MetricsProcessorFactory.getThreadMetricsProcessor(processorType);
        JvmBufferPoolMetricsProcessor bufferPoolProcessor = MetricsProcessorFactory.getBufferPoolMetricsProcessor(processorType);
        Scheduler scheduler = new JvmMetricsScheduler(classProcessor, gcProcessor, memoryProcessor, bufferPoolProcessor, threadProcessor);
        long startMills = System.currentTimeMillis();
        scheduler.run(startMills, startMills + 60 * 1000);
    }

    private void init() {
        ProfilingConfig config = ProfilingConfig.getInstance();
        config.setMethodMetricsFile("/tmp/metrics.log");
        config.setClassMetricsFile("/tmp/metrics.log");
        config.setGcMetricsFile("/tmp/metrics.log");
        config.setMemoryMetricsFile("/tmp/metrics.log");
        config.setBufferPoolMetricsFile("/tmp/metrics.log");
        config.setThreadMetricsFile("/tmp/metrics.log");
        config.setLogRollingTimeUnit("DAILY");
    }
}
