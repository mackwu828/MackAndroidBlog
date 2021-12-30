package com.mackwu.plugin.base;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ===================================================
 * Created by MackWu on 2021/10/28 16:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseMethodVisitor extends MethodVisitor {

    /**
     * 方法执行前插入
     */
    public abstract void visitBeforeCode();

    /**
     * 方法执行后插入
     */
    public abstract void visitAfterCode();


    public BaseMethodVisitor(MethodVisitor methodVisitor) {
        super(AsmConstant.ASM_API_VERSION, methodVisitor);
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

    /**
     * 方法访问结束
     */
    @Override
    public void visitEnd() {
        super.visitEnd();
    }

}
