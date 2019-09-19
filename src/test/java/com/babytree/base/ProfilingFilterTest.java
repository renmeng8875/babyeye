package com.babytree.base;

import com.babytree.babyeye.base.config.ProfilingFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProfilingFilterTest {

    @Before
    public void init() {
        ProfilingFilter.addIncludePackage("org.junit");
        ProfilingFilter.addExcludePackage("org.junit.rules");
        ProfilingFilter.addExcludeMethods("hello");
        ProfilingFilter.addExcludeClassLoader("org.apache.catalina.loader.WebappClassLoader");
    }

    @Test
    public void test() {
        Assert.assertFalse(ProfilingFilter.isNeedInject("org.junit.Before"));
        Assert.assertTrue(ProfilingFilter.isNeedInject("org/junit/Before"));
        Assert.assertTrue(ProfilingFilter.isNotNeedInject("org/junit/rules/ErrorCollector"));

        Assert.assertTrue(ProfilingFilter.isNotNeedInjectMethod("toString"));
        Assert.assertTrue(ProfilingFilter.isNotNeedInjectMethod("hello"));
        Assert.assertFalse(ProfilingFilter.isNotNeedInjectMethod("assertFalse"));

        Assert.assertTrue(ProfilingFilter.isNotNeedInjectClassLoader("org.apache.catalina.loader.WebappClassLoader"));
        Assert.assertFalse(ProfilingFilter.isNotNeedInjectClassLoader("org.springframework.boot.loader.LaunchedURLClassLoader"));
    }

}
