package com.babytree.base;

import org.junit.Assert;
import org.junit.Test;

import static com.babytree.babyeye.base.util.NumFormatUtils.getFormatStr;

public class NumFormatUtilsTest {

    @Test
    public void test() {
        Assert.assertEquals("10011.22", getFormatStr(10011.22222D));
        Assert.assertEquals("10011.22", getFormatStr(10011.22D));
        Assert.assertEquals("1.22", getFormatStr(1.2222D));
        Assert.assertEquals("1.20", getFormatStr(1.2D));
        Assert.assertEquals("1.00", getFormatStr(1D));
        Assert.assertEquals("0.00", getFormatStr(0D));
        Assert.assertEquals("-1.00", getFormatStr(-1D));
        Assert.assertEquals("-1.10", getFormatStr(-1.1D));
    }
}
