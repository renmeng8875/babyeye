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
        JvmThreadMetricsProcessor threadProcessor = MetricsProcessorFactory.getThreadMetricsProcessor();
        Scheduler scheduler = new JvmMetricsScheduler( threadProcessor);
        long startMills = System.currentTimeMillis();
        scheduler.run(startMills, startMills + 60 * 1000);
    }

    private void init() {
        ProfilingConfig config = ProfilingConfig.getInstance();
        config.setMethodMetricsFile("/tmp/metrics.log");
        config.setThreadMetricsFile("/tmp/metrics.log");
        config.setLogRollingTimeUnit("DAILY");
    }
}
