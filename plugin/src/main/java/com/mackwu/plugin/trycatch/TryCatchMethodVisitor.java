package com.mackwu.plugin.trycatch;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * ===================================================
 * Created by MackWu on 2021/10/29 14:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TryCatchMethodVisitor extends AdviceAdapter {

    private final Label label0 = new Label();
    private final Label label1 = new Label();
    private final Label label2 = new Label();
    private final String descriptor;

    protected TryCatchMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(AsmConstant.ASM_API_VERSION, methodVisitor, access, name, descriptor);
        this.descriptor = descriptor;
    }

    /**
     * 刚进入方法
     */
    @Override
    protected void onMethodEnter() {
        visitTryCatchBlock(label0, label1, label2, "java/lang/Exception");
        visitLabel(label0);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        visitLabel(label1);
        visitLabel(label2);
        int local = newLocal(Type.getType("Ljava/lang/Exception;"));
        visitVarInsn(ASTORE, local);
        visitVarInsn(ALOAD, local);
        visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false);
        visitInsn(getReturnCode());
        super.visitMaxs(maxStack, maxLocals);
    }

    /**
     * 刚出方法
     */
    @Override
    protected void onMethodExit(int opcode) {
    }

    /**
     * 获取返回值
     */
    private int getReturnCode() {
        int returnCode;
        // V: void
        if (descriptor.endsWith("V")) {
            returnCode = RETURN;
        }
        // I: int
        // Z: boolean
        // B: byte
        // C: char
        // S: short
        else if (descriptor.endsWith("I") ||
                descriptor.endsWith("Z") ||
                descriptor.endsWith("B") ||
                descriptor.endsWith("C") ||
                descriptor.endsWith("S")) {
            visitInsn(ICONST_0);
            returnCode = IRETURN;
        }
        // J: long
        else if (descriptor.endsWith("J")) {
            visitInsn(LCONST_0);
            returnCode = LRETURN;
        }
        // F: float
        else if (descriptor.endsWith("F")) {
            visitInsn(FCONST_0);
            returnCode = FRETURN;
        }
        // D: double
        else if (descriptor.endsWith("D")) {
            visitInsn(DCONST_0);
            returnCode = DRETURN;
        }
        // String、Object
        else {
            visitInsn(ACONST_NULL);
            returnCode = ARETURN;
        }
        System.out.println("returnCode: " + returnCode);
        return returnCode;
    }

}
