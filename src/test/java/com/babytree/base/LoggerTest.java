package com.babytree.base;


import com.babytree.babyeye.base.util.Logger;
import org.junit.Test;

public class LoggerTest {

    @Test
    public void test() {
        Logger.setDebugEnable(true);
        Logger.debug("debug test");
        Logger.info("info test");
        Logger.warn("warn test");
        Logger.error("error test");
//        Logger.error("error test", new UnknownError());
    }
}
