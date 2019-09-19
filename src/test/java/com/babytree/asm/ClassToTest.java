package com.babytree.asm;



import com.babytree.babyeye.base.util.ThreadUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class ClassToTest {

    public String getStr() {
        ThreadUtils.sleepQuietly(ThreadLocalRandom.current().nextInt(10), TimeUnit.MILLISECONDS);
        return "111";
    }
}
