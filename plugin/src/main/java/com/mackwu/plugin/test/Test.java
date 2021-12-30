package com.mackwu.plugin.test;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * ===================================================
 * Created by MackWu on 2021/11/1 11:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Test {

    public static void main(String[] args) {
        Person person = new Person();
        try {
            // classReader
            ClassReader classReader = new ClassReader(Person.class.getCanonicalName());
            // classWriter
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            // testClassVisitor
            TestClassVisitor cv = new TestClassVisitor(classWriter);
            // accept
            classReader.accept(cv, ClassReader.EXPAND_FRAMES);


            //
            String path = Objects.requireNonNull(Person.class.getResource("")).getPath();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile() + File.separator + "Person.class");
            fileOutputStream.write(classWriter.toByteArray());
            fileOutputStream.close();

            person.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
