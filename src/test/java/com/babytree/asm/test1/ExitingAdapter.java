package com.babytree.asm.test1;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


public class ExitingAdapter extends AdviceAdapter {

    private String name;

    public ExitingAdapter(MethodVisitor mv, int acc, String name, String desc) {
        super(Opcodes.ASM5, mv, acc, name, desc);
        this.name = name;
    }

    public void onMethodExit(int opcode) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        if (opcode == ATHROW) {
            mv.visitLdcInsn("Exiting on exception " + name);
        } else {
            mv.visitLdcInsn("Exiting " + name);
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }
}
