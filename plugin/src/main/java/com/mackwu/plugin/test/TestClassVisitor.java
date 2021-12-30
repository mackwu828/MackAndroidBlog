package com.mackwu.plugin.test;

import com.mackwu.plugin.AsmConstant;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.ModuleVisitor;
import org.objectweb.asm.util.CheckMethodAdapter;

import java.util.Arrays;

/**
 * ===================================================
 * Created by MackWu on 2021/11/1 11:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TestClassVisitor extends ClassVisitor {

    public TestClassVisitor(ClassVisitor classVisitor) {
        super(AsmConstant.ASM_API_VERSION, classVisitor);
    }

    /**
     * 访问类的头部
     *
     * @param version    JDK版本。如50(JDK1.6)、51(JDK1.7)、52(JDK1.8)
     * @param access     类的访问权限。如public、private
     * @param name       类名
     * @param signature  类的签名。如果类不是泛型或者没有继承泛型类，那么值为空
     * @param superName  父类的名称
     * @param interfaces 实现的接口
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("visit...");
        System.out.println(
                "version: " + version +
                        ", access: " + access +
                        ", name: " + name +
                        ", signature: " + signature +
                        ", superName: " + superName +
                        ", interfaces: " + Arrays.toString(interfaces)
        );
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
        System.out.println("visitSource...");
    }

    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        System.out.println("visitModule...");
        return super.visitModule(name, access, version);
    }

    /**
     * 访问类的属性
     */
    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("visitField...");
        return super.visitField(access, name, descriptor, signature, value);
    }

    /**
     * 访问类的方法
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("visitMethod...");
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        return new TestMethodVisitor(mv, access, name, descriptor);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println("visitEnd...");
    }

}
