package com.mackwu.plugin.trycatch

import com.mackwu.plugin.base.BaseTransform
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter

import java.util.jar.JarOutputStream

class TryCatchTransform extends BaseTransform {

    @Override
    String getName() {
        return 'TryCatch'
    }

    @Override
    void transformFile(File file) {
        def name = file.name
        println 'transformFile...  name=' + name
        if (name.endsWith("JavaTest.class") || name.endsWith("KTest.class") ) {
            def destBytes = visitSource(file.bytes)
            def fileOutputStream = new FileOutputStream(file.parentFile.absolutePath + File.separator + name)
            fileOutputStream.write(destBytes)
            fileOutputStream.close()
        }
    }

    @Override
    void transformJar(String entryName, JarOutputStream jarOutputStream, byte[] sourceBytes) {
        if (entryName.endsWith("OkHttpClient\$Builder.class")) {
            println 'transformJar...  entryName=' + entryName
            def destBytes = visitSource(sourceBytes)
            jarOutputStream.write(destBytes)
        }
        jarOutputStream.write(sourceBytes)
    }

    static byte[] visitSource(byte[] sourceBytes) {
        def classReader = new ClassReader(sourceBytes)
        def classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        def cv = new TryCatchClassVisitor(classWriter)
        classReader.accept(cv, ClassReader.EXPAND_FRAMES)
        return classWriter.toByteArray()
    }

}