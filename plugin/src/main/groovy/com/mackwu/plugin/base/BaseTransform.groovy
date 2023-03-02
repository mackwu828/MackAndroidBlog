package com.mackwu.plugin.base

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager

import java.util.jar.JarOutputStream

abstract class BaseTransform extends Transform {

    /**
     * 自定义Transform对应的Task名称。
     * Task :component:transformClassesWithBaseForAaaDebug UP-TO-DATE
     *
     * See {@link TransformManager#getTaskNamePrefix}
     */
    @Override
    String getName() {
        return 'base'
    }

    /**
     * 输入的类型，指定要处理的文件类型。
     * 有两种类型：目录和jar包
     * 处理 java 的 class 文件。See {@link TransformManager#CONTENT_CLASS}
     * 处理 java 的资源文件。See {@link TransformManager#CONTENT_RESOURCES}
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 作用范围。
     * 只有项目内容。See {@link TransformManager#SCOPE_FULL_PROJECT}
     */
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 是否是增量编译
     */
    @Override
    boolean isIncremental() {
        return true
    }

    @Override
    void transform(TransformInvocation transformInvocation) {
        println 'transform...  start'

        // 获取输入流
        def inputs = transformInvocation.inputs
        // 获取输出目录
        def outputProvider = transformInvocation.outputProvider
        // 是否是增量编译
        def isIncremental = transformInvocation.isIncremental()

        // 非增量编时删除所有输出目录
        if (!isIncremental) {
            if (outputProvider != null) {
                outputProvider.deleteAll()
            }
        }

        // 遍历输入流
        inputs.each { input ->
            def directoryInputProcessor = new DirectoryInputProcessor(this)
            def jarInputProcessor = new JarInputProcessor(this)

            // directoryInputs
            input.directoryInputs.each { directorInput ->
                directoryInputProcessor.processDirectoryInputs(directorInput, outputProvider, isIncremental)
            }

            // jarInputs
            input.jarInputs.each { jarInput ->
                jarInputProcessor.processJarInputs(jarInput, outputProvider, isIncremental)
            }
        }

        println 'transform...  end'
    }

    abstract void transformFile(File file)

    abstract void transformJar(String name, JarOutputStream jarOutputStream, byte[] sourceBytes)

}