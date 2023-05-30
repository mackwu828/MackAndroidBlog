package com.mackwu.image.transition.util;

import android.content.Context;
import android.opengl.GLES20;

import androidx.annotation.RawRes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author MackWu
 * @since 2023/3/3 17:54
 */
public class ShaderLoader {

    /**
     * 加载着色器
     *
     * @param shaderType 着色器类型。
     *                   GL_VERTEX_SHADER 顶点着色器
     *                   GL_FRAGMENT_SHADER 片段着色器
     * @param shaderCode 着色器源码
     * @return 着色器
     */
    public static int loadShader(int shaderType, String shaderCode) {
        Logger.d("loadShader...  shaderType=" + shaderType);
        // 创建着色器对象
        int shader = GLES20.glCreateShader(shaderType);
        // 创建失败
        if (shader == 0) {
            return 0;
        }
        // 添加着色器源码
        GLES20.glShaderSource(shader, shaderCode);
        // 编译着色器源码
        GLES20.glCompileShader(shader);
        // 获取编译状态
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        // 编译失败
        if (compiled[0] == 0) {
            Logger.d("loadShader failed... " + GLES20.glGetShaderInfoLog(shader));
            // 删除着色器
            GLES20.glDeleteShader(shader);
            return 0;
        }
        Logger.d("loadShader success...  shader=" + shader);
        return shader;
    }


    /**
     * 加载着色器
     *
     * @param shaderType 着色器类型。
     *                   GL_VERTEX_SHADER 顶点着色器
     *                   GL_FRAGMENT_SHADER 片段着色器
     * @param context    context
     * @param resId      着色器源码资源id
     * @return 着色器
     */
    public static int loadShader(int shaderType, Context context, @RawRes int resId) {
        return loadShader(shaderType, loadShaderCode(context, resId));
    }

    private static String loadShaderCode(Context context, @RawRes int resId) {
        StringBuilder res = new StringBuilder();
        BufferedReader br = null;
        try {
            InputStream inputStream = context.getResources().openRawResource(resId);
            InputStreamReader isr = new InputStreamReader(inputStream);
            br = new BufferedReader(isr);
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                res.append(nextLine).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res.toString();
    }

}
