package com.babytree.babyeye.base.metric.processor.discard;

import com.babytree.babyeye.base.metric.JvmClassMetrics;
import com.babytree.babyeye.base.metric.processor.AbstractJvmClassMetricsProcessor;

public class DiscardJvmClassMetricsProcessor extends AbstractJvmClassMetricsProcessor {

    @Override
    public void process(JvmClassMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

}
