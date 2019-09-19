package com.babytree.base;

import com.babytree.babyeye.base.config.ProfilingParams;
import org.junit.Assert;
import org.junit.Test;


public class ProfilingParamsTest {

    @Test
    public void test() {
        ProfilingParams params = ProfilingParams.of(1000, 10);
        Assert.assertEquals(params.getMostTimeThreshold(), 1000);
        Assert.assertNotEquals(params.getMostTimeThreshold(), -1000);

        Assert.assertEquals(params.getOutThresholdCount(), 10);
        Assert.assertNotEquals(params.getOutThresholdCount(), -10);
    }
}
