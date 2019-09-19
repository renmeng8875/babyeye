package com.babytree.babyeye.asm;

import com.babytree.babyeye.asm.aop.ProfilingTransformer;

import java.lang.instrument.Instrumentation;


public class PreMain {

    public static void premain(String options, Instrumentation ins) {
        if (ASMBootstrap.getInstance().initial()) {
            ins.addTransformer(new ProfilingTransformer());
        }
    }

}
