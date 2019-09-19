package com.babytree.babyeye.base.metric.processor;

import com.babytree.babyeye.base.config.ProfilingConfig;
import com.babytree.babyeye.base.log.ILogger;
import com.babytree.babyeye.base.log.LoggerFactory;

public abstract class AbstractJvmGCMetricsProcessor implements JvmGCMetricsProcessor {

    protected ILogger logger = LoggerFactory.getLogger(ProfilingConfig.getInstance().getGcMetricsFile());

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        logger.flushLog();
    }
}
