package com.babytree.babyeye.base.metric.processor.discard;

import com.babytree.babyeye.base.metric.JvmThreadMetrics;
import com.babytree.babyeye.base.metric.processor.AbstractJvmThreadMetricsProcessor;

public class DiscardJvmThreadMetricsProcessor extends AbstractJvmThreadMetricsProcessor {

    @Override
    public void process(JvmThreadMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

}
