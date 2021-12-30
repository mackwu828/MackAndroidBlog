package com.mackwu.plugin.lifecycle;


import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ===================================================
 * Created by MackWu on 2021/10/25 17:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LifecycleMethodVisitor extends MethodVisitor {

    private final String tag;
    private final String msg;

    public LifecycleMethodVisitor(MethodVisitor methodVisitor, String tag, String msg) {
        super(AsmConstant.ASM_API_VERSION, methodVisitor);
        this.tag = tag;
        this.msg = msg;
    }

    /**
     * 开始访问方法
     */
    @Override
    public void visitCode() {
        visitBeforeCode(); // 方法执行前插入
        super.visitCode();
    }

    /**
     * 访问一个字节码指令
     */
    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
            visitAfterCode(); // 方法执行后插入
        }
        super.visitInsn(opcode);
    }

    private void visitBeforeCode() {
        mv.visitLdcInsn(tag);
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn(msg);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }

    private void visitAfterCode() {
    }

}
