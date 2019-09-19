package com.babytree.base;

import com.babytree.babyeye.base.util.MapUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MapUtilsTest {

    @Test
    public void test() {
        Map<Object, Object> hashMap = MapUtils.createHashMap(1);
        Assert.assertNotNull(hashMap);

        Map<Object, Object> hashMap2 = MapUtils.createHashMap(10, 0.01F);
        Assert.assertNotNull(hashMap2);

        ConcurrentHashMap<Object, Object> concHashMap = MapUtils.createConcHashMap(10, 0.1F);
        Assert.assertNotNull(concHashMap);
    }
}
