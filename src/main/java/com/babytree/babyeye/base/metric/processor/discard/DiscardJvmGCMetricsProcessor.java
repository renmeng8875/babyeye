package com.babytree.babyeye.base.metric.processor.discard;

import com.babytree.babyeye.base.metric.JvmGCMetrics;
import com.babytree.babyeye.base.metric.processor.AbstractJvmGCMetricsProcessor;

public class DiscardJvmGCMetricsProcessor extends AbstractJvmGCMetricsProcessor {

    @Override
    public void process(JvmGCMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

}
