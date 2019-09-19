package com.babytree.babyeye.asm;

import com.babytree.babyeye.asm.aop.ProfilingTransformer;

import java.lang.instrument.Instrumentation;



//-javaagent:/git-repositories/babyeye/target/babyeye-1.0-SNAPSHOT.jar -DMyPerf4JPropFile=/monitor.properties
public class PreMain {

    public static void premain(String options, Instrumentation ins) {
        if (ASMBootstrap.getInstance().initial()) {
            ins.addTransformer(new ProfilingTransformer());
        }
    }

}
