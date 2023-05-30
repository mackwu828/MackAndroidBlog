package com.mackwu.image.transition.util;

import android.opengl.GLES20;

/**
 * @author MackWu
 * @since 2023/3/3 18:16
 */
public class ProgramLoader {

    /**
     * 加载程序
     *
     * @param vertexShader   顶点着色器
     * @param fragmentShader 片元着色器
     * @return 程序
     */
    public static int loadProgram(int vertexShader, int fragmentShader) {
        Logger.d("loadProgram...");
        // 创建空程序
        int program = GLES20.glCreateProgram();
        // 创建失败
        if (program == 0) {
            return 0;
        }
        // 添加着色器
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        // 创建可执行程序
        GLES20.glLinkProgram(program);
        // 获取程序状态
        int[] result = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, result, 0);
        if (result[0] == 0) {
            Logger.d("loadProgram failed... " + GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteProgram(program);
            return 0;
        }
        Logger.d("loadProgram success...  program=" + program);
        return program;
    }

}
