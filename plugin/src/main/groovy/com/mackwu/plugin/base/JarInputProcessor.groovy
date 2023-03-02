package com.mackwu.plugin.base

import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.Status
import com.android.build.api.transform.TransformOutputProvider
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream

class JarInputProcessor {

    private BaseTransform transform

    JarInputProcessor(BaseTransform transform) {
        this.transform = transform
    }

    /**
     * 遍历jar包中的class文件。jarInputs代表以jar包方式参与项目编译的所有本地jar包或远程jar包。
     *
     * 一、jarInput.file的绝对路径
     *  <code>
     *      jarInput.file.absolutePath
     *  </code>
     *
     *  1. 本地依赖的jar包。如libs下添加的jar包
     *  C:\Users\wumen\.gradle\caches\transforms-3\7aefa443574682ff83d2f3a5c772c141\transformed\jetified-plugin.jar
     *
     *  2. 远程依赖的jar包。如dependencies下添加的依赖，会在.gradle下生成缓存。
     *  C:\Users\wumen\.gradle\caches\transforms-3\47b100ae7c2250c8731d5243211d2e1c\transformed\jetified-viewbinding-7.0.2-runtime.jar
     *  C:\Users\wumen\.gradle\caches\transforms-3\6e455b7f55314be9be88190eba7a4de1\transformed\jetified-kotlin-android-extensions-runtime-1.5.31.jar
     *  ...
     *
     *  3. classes.jar
     *  C:\Android\workspace\mackwu\BaseCore\base-core\build\intermediates\runtime_library_classes_jar\debug\classes.jar
     *
     *  4. R.jar
     *  C:\Android\workspace\mackwu\MackAndroidBlog\component\build\intermediates\compile_and_runtime_not_namespaced_r_class_jar\aaaDebug\R.jar
     *
     * @param jarInput
     * @param outputProvider
     */
    void processJarInputs(JarInput jarInput, TransformOutputProvider outputProvider, boolean isIncremental) {
        println 'processJarInputs...  isIncremental: ' + isIncremental

        // 输入目录
        def sourceFile = jarInput.file
        println 'sourceDir: ' + sourceFile.absolutePath
        // 目标目录
        def destFile = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
        println 'destFile: ' + destFile.absolutePath

        // 是否增量编译
        if (!isIncremental) {
            // 非增量编译
            transformJar(sourceFile, destFile)
        } else {
            // 增量编译
            def status = jarInput.status
            // ADDED、CHANGED
            if (status == Status.ADDED || status == Status.CHANGED) {
                transformJar(sourceFile, destFile)
            }
            // REMOVED
            else if (status == Status.REMOVED) {
                if (destFile.exists()) {
                    FileUtils.forceDelete(destFile)
                }
            }
            // NOTCHANGED
            else if (status == Status.NOTCHANGED) {
                // ignored
            }
        }
    }

    private void transformJar(File sourceFile, File destFile) {
        // 空的jar文件不进行处理
        if (sourceFile == null || sourceFile.length() == 0L) {
            println sourceFile.absolutePath + ' is null'
            return
        }

        // 目标jar文件输出流
        def jarOutputStream = new JarOutputStream(new FileOutputStream(destFile))
        // 遍历源jar文件
        def sourceJarFile = new JarFile(sourceFile)
        def entries = sourceJarFile.entries()
        while (entries.hasMoreElements()) {
            def jarEntry = entries.nextElement()
            def inputStream = sourceJarFile.getInputStream(jarEntry)
            def entryName = jarEntry.getName()

            //
            def tempJarEntry = new JarEntry(entryName)
            jarOutputStream.putNextEntry(tempJarEntry)

            //
            transform.transformJar(entryName, jarOutputStream, IOUtils.toByteArray(inputStream))

            inputStream.close()
            jarOutputStream.closeEntry()
        }
        jarOutputStream.close()
        sourceJarFile.close()
    }

}