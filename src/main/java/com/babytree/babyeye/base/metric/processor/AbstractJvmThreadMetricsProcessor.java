package com.babytree.babyeye.base.metric.processor;

import com.babytree.babyeye.base.config.ProfilingConfig;
import com.babytree.babyeye.base.log.ILogger;
import com.babytree.babyeye.base.log.LoggerFactory;

public abstract class AbstractJvmThreadMetricsProcessor implements JvmThreadMetricsProcessor {

    protected ILogger logger = LoggerFactory.getLogger(ProfilingConfig.getInstance().getThreadMetricsFile());

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        logger.flushLog();
    }
}
