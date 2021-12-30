package com.mackwu.plugin.lifecycle;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * ===================================================
 * Created by MackWu on 2021/10/25 15:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LifecycleClassVisitor extends ClassVisitor {

    public LifecycleClassVisitor(ClassVisitor classVisitor) {
        super(AsmConstant.ASM_API_VERSION, classVisitor);
    }

    /**
     * 访问类的方法
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("visitMethod...  name: " + name);
        // methodVisitor
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if ("onCreate".equals(name) ||
                "onDestroy".equals(name) ||
                "initView".equals(name)
        ) {
            return new LifecycleMethodVisitor(mv, "TAG", name + "...  ");
        }
        return mv;
    }

}
