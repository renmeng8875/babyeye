package com.babytree.babyeye.asm.aop;

import com.babytree.babyeye.base.config.ProfilingConfig;
import com.babytree.babyeye.base.config.ProfilingFilter;
import com.babytree.babyeye.base.util.Logger;
import org.objectweb.asm.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;


public class ProfilingClassAdapter extends ClassVisitor {

    private String innerClassName;

    private boolean isInterface;

    private List<String> fieldNameList = new ArrayList<>();

    public ProfilingClassAdapter(final ClassVisitor cv, String innerClassName) {
        super(ASM5, cv);
        this.innerClassName = innerClassName;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        Logger.debug("ProfilingClassAdapter.visit(" + version + ", " + access + ", " + name + ", " + signature + ", " + superName + ", " + Arrays.toString(interfaces) + ")");

        super.visit(version, access, name, signature, superName, interfaces);
        this.isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        String upFieldName = name.substring(0, 1).toUpperCase() + name.substring(1);
        fieldNameList.add("get" + upFieldName);
        fieldNameList.add("set" + upFieldName);
        fieldNameList.add("is" + upFieldName);

        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String desc,
                                     String signature,
                                     String[] exceptions) {
        if (isInterface || !isNeedVisit(access, name)) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (mv == null) {
            return null;
        }
        Logger.debug("ProfilingClassAdapter.visitMethod(" + access + ", " + name + ", " + desc + ", " + signature + ", " + Arrays.toString(exceptions) + "), innerClassName=" + innerClassName);

        return new ProfilingMethodVisitor(access, name, desc, mv, innerClassName);
    }

    private boolean isNeedVisit(int access, String name) {
        //不对私有方法进行注入
        if ((access & ACC_PRIVATE) != 0 && ProfilingConfig.getInstance().isExcludePrivateMethod()) {
            return false;
        }

        //不对抽象方法、native方法、桥接方法、合成方法进行注入
        if ((access & ACC_ABSTRACT) != 0
                || (access & ACC_NATIVE) != 0
                || (access & ACC_BRIDGE) != 0
                || (access & ACC_SYNTHETIC) != 0) {
            return false;
        }

        if ("<init>".equals(name) || "<clinit>".equals(name)) {
            return false;
        }

        if (fieldNameList.contains(name) || ProfilingFilter.isNotNeedInjectMethod(name)) {
            return false;
        }

        return true;
    }
}
