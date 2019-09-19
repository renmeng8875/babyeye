package com.babytree.babyeye.core.scheduler;

import com.babytree.babyeye.base.Scheduler;
import com.babytree.babyeye.base.metric.*;
import com.babytree.babyeye.base.metric.processor.*;

import java.lang.management.*;

public class JvmMetricsScheduler implements Scheduler {


    private JvmThreadMetricsProcessor threadMetricsProcessor;


    public JvmMetricsScheduler(JvmThreadMetricsProcessor threadMetricsProcessor) {

        this.threadMetricsProcessor = threadMetricsProcessor;
    }

    @Override
    public void run(long lastTimeSliceStartTime, long millTimeSlice) {
        long stopMillis = lastTimeSliceStartTime + millTimeSlice;
        processJVMThreadMetrics(lastTimeSliceStartTime, lastTimeSliceStartTime, stopMillis);
    }

    private void processJVMThreadMetrics(long processId, long startMillis, long stopMillis) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        threadMetricsProcessor.beforeProcess(processId, startMillis, stopMillis);
        try {
            threadMetricsProcessor.process(new JvmThreadMetrics(threadMXBean), processId, startMillis, stopMillis);
        } finally {
            threadMetricsProcessor.afterProcess(processId, startMillis, stopMillis);
        }
    }
}
