package com.mackwu.plugin.test;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * ===================================================
 * Created by MackWu on 2021/11/1 11:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TestMethodVisitor extends AdviceAdapter {

    private Label handlerLabel;

    protected TestMethodVisitor(MethodVisitor mv, int access, String name, String descriptor) {
        super(AsmConstant.ASM_API_VERSION, mv, access, name, descriptor);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        this.handlerLabel = handler;
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
//        System.out.println("visitMaxs...  maxStack: " + maxStack + ", maxLocals: " + maxLocals);
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

            // IOUtil.close(sw, pw);
            visitInsn(ICONST_2);
            visitTypeInsn(ANEWARRAY, "java/io/Closeable");
            visitInsn(DUP);
            visitInsn(ICONST_0);
            visitVarInsn(ALOAD, 2);
            visitInsn(AASTORE);
            visitInsn(DUP);
            visitInsn(ICONST_1);
            visitVarInsn(ALOAD, 3);
            visitInsn(AASTORE);
            visitMethodInsn(INVOKESTATIC, "com/mackwu/plugin/test/IOUtil", "close", "([Ljava/io/Closeable;)V", false);

            // String newStackTrace = stackTrace.replace("\n","<br/>");
            visitVarInsn(ALOAD, 4);
            visitLdcInsn("\n");
            visitLdcInsn("<br/>");
            visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "replace", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", false);
            visitVarInsn(ASTORE, 5);

            // System.out.println(newStackTrace);
            visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            visitVarInsn(ALOAD, 5);
            visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            visitInsn(RETURN);
        }
    }

}
