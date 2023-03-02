package com.mackwu.plugin.trycatch;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * ===================================================
 * Created by MackWu on 2021/10/29 14:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TryCatchMethodVisitor2 extends AdviceAdapter {

    private Label handlerLabel;

    TryCatchMethodVisitor2(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(AsmConstant.ASM_API_VERSION, methodVisitor, access, name, descriptor);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        this.handlerLabel = handler;
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
        if (handlerLabel != null) {
            visitLabel(handlerLabel);

            // Exception
            visitInsn(DUP);
            visitVarInsn(ASTORE, 1);

            // StringWriter sw = new StringWriter();
            visitTypeInsn(NEW, "java/io/StringWriter");
            visitInsn(DUP);
            visitMethodInsn(INVOKESPECIAL, "java/io/StringWriter", "<init>", "()V", false);
            visitVarInsn(Opcodes.ASTORE, 2);

            //  PrintWriter pw = new PrintWriter(sw);
            visitTypeInsn(NEW, "java/io/PrintWriter");
            visitInsn(DUP);
            visitVarInsn(ALOAD, 2);
            visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/io/Writer;)V", false);
            visitVarInsn(ASTORE, 3);

            // e.printStackTrace(pw);
            visitVarInsn(ALOAD, 1);
            visitVarInsn(ALOAD, 3);
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "(Ljava/io/PrintWriter;)V", false);

            // String stackTrace = sw.toString();
            visitVarInsn(ALOAD, 2);
            visitMethodInsn(INVOKEVIRTUAL, "java/io/StringWriter", "toString", "()Ljava/lang/String;", false);
            visitVarInsn(ASTORE, 4);

            // System.out.println(stackTrace);
            visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            visitVarInsn(ALOAD, 4);
            visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            // RETURN
            visitInsn(RETURN);
        }
    }

}
