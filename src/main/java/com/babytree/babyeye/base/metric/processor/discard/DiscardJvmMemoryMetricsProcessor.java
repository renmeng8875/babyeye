package com.babytree.babyeye.base.metric.processor.discard;

import com.babytree.babyeye.base.metric.JvmMemoryMetrics;
import com.babytree.babyeye.base.metric.processor.AbstractJvmMemoryMetricsProcessor;

public class DiscardJvmMemoryMetricsProcessor extends AbstractJvmMemoryMetricsProcessor {

    @Override
    public void process(JvmMemoryMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

}
