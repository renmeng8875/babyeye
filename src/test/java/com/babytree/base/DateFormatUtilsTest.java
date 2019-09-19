package com.babytree.base;

import com.babytree.babyeye.base.util.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatUtilsTest {

    @Test
    public void test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long mills = System.currentTimeMillis();
        Assert.assertEquals(DateFormatUtils.format(mills), format.format(new Date(mills)));
    }

}
