package com.mackwu.plugin.base

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.Status
import com.android.build.api.transform.TransformOutputProvider
import com.mackwu.plugin.base.BaseTransform
import org.apache.commons.io.FileUtils

class DirectoryInputProcessor {

    private BaseTransform transform

    DirectoryInputProcessor(BaseTransform transform) {
        this.transform = transform
    }

    /**
     * 遍历文件夹中的class文件。directoryInputs代表着以源码方式参与项目编译的所有目录结构及其目录下的源码文件。
     * 比如我们手写的类以及R.class、BuildConfig.class以及R$XXX.class等。
     *
     *
     * sourceDir: C:\Android\workspace\mackwu\MackAndroidBlog\component\build\intermediates\javac\aaaDebug\classes
     * destDir: C:\Android\workspace\mackwu\MackAndroidBlog\component\build\intermediates\transforms\Lifecycle\aaa\debug\93
     *
     * sourceDir: C:\Android\workspace\mackwu\MackAndroidBlog\component\build\tmp\kotlin-classes\aaaDebug
     * destDir: C:\Android\workspace\mackwu\MackAndroidBlog\component\build\intermediates\transforms\Lifecycle\aaa\debug\94
     *
     * changedFile:  status: CHANGED, destPath: C:\Android\workspace\mackwu\MackAndroidBlog\component\build\intermediates\transforms\Lifecycle\aaa\debug\93\com\mackwu\component\ui\MainActivity.class
     *
     * @param directoryInput 文件
     * @param outputProvider
     */
    void processDirectoryInputs(DirectoryInput directoryInput, TransformOutputProvider outputProvider, boolean isIncremental) {
        println 'processDirectoryInputs...  isIncremental: ' + isIncremental

        // 输入目录
        def sourceDir = directoryInput.file
        println 'sourceDir: ' + sourceDir.absolutePath
        // 输出目录
        def destDir = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
        println 'destDir: ' + destDir.absolutePath

        // 是否增量编译
        if (!isIncremental) {
            transformDirectory(sourceDir)
            // 将源目录到目标目录
            FileUtils.copyDirectory(sourceDir, destDir)
        } else {
            def changedFiles = directoryInput.changedFiles
            for (changedFile in changedFiles) {
                def status = changedFile.value
                def inputFile = changedFile.key
                def destPath = inputFile.absolutePath.replace(directoryInput.file.absolutePath, destDir.absolutePath)
                def destFile = new File(destPath)
                println 'changedFile:  status: ' + status + ", destPath: " + destPath

                // ADDED、CHANGED
                if (status == Status.ADDED || status == Status.CHANGED) {
                    transformDirectory(inputFile)
                    FileUtils.touch(destFile)
                    FileUtils.copyFile(inputFile, destFile)
                }
                // REMOVED
                else if (status == Status.REMOVED) {
                    if (destFile.exists()) {
                        destFile.delete()
                    }
                }
                // NOTCHANGED
                else if (status == Status.NOTCHANGED) {
                    // ignored
                }
            }
        }
    }

    private void transformDirectory(File sourceDir) {
        if (sourceDir.isDirectory()) {
            sourceDir.eachFileRecurse { file ->
                transform.transformFile(file)
            }
        } else {
            transform.transformFile(sourceDir)
        }
    }

}