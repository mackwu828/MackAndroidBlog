package com.mackwu.plugin.trycatch;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * ===================================================
 * Created by MackWu on 2021/10/29 14:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TryCatchClassVisitor extends ClassVisitor {

    public TryCatchClassVisitor(ClassVisitor classVisitor) {
        super(AsmConstant.ASM_API_VERSION, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        System.out.println("visitMethod...  name=" + name);
        if (name.contains("addNetworkInterceptor")) {
            return new TryCatchMethodVisitor(mv, access, name, descriptor);
        }
        return mv;
    }

}
