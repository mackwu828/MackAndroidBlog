package com.mackwu.plugin.lifecycle

import com.mackwu.plugin.base.BaseTransform
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter

import java.util.jar.JarOutputStream

class LifecycleTransform extends BaseTransform {

    @Override
    String getName() {
        return 'Lifecycle'
    }

    @Override
    void transformFile(File file) {
        def name = file.name
        if (name.endsWith(".class") && name == "MainActivity.class") {
            def destBytes = visitSource(file.bytes)
            def fileOutputStream = new FileOutputStream(file.parentFile.absolutePath + File.separator + name)
            fileOutputStream.write(destBytes)
            fileOutputStream.close()
        }
    }

    @Override
    void transformJar(String entryName, JarOutputStream jarOutputStream, byte[] sourceBytes) {
        if (entryName.endsWith(".class") && entryName == "androidx/fragment/app/FragmentActivity.class") {
            println '----------- start deal with jar class file <' + entryName + '> -----------'
            def destBytes = visitSource(sourceBytes)
            jarOutputStream.write(destBytes)
        } else {
            jarOutputStream.write(sourceBytes)
        }
    }

    static byte[] visitSource(byte[] sourceBytes) {
        def classReader = new ClassReader(sourceBytes)
        def classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        def cv = new LifecycleClassVisitor(classWriter)
        classReader.accept(cv, ClassReader.EXPAND_FRAMES)
        return classWriter.toByteArray()
    }

}