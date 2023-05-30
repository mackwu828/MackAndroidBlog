package com.mackwu.image.transition;

import android.content.Context;
import android.opengl.GLES20;

import com.mackwu.image.transition.util.ProgramLoader;
import com.mackwu.image.transition.util.ShaderLoader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author MackWu
 * @since 2023/3/3 19:28
 */
public class Triangle {

    // 三角形的坐标
    private final float[] triangleCoordinates = {
            0.0f, 0.5f,
            -0.5f, -0.5f,
            0.5f, -0.5f,
    };
    // 每个顶点的坐标数。1个顶点由2个坐标决定，即二维坐标x轴坐标和y轴坐标
    private static final int COORDINATES_PER_VERTEX = 2;
    // 顶点缓存
    private final FloatBuffer vertexBuffer;
    // 顶点数量
    private final int vertexCount = triangleCoordinates.length / COORDINATES_PER_VERTEX;

    // 程序
    private final int program;
    // 颜色。rgb和alpha
    private final float[] color = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Triangle(Context context) {
        // 分配native内存，每个浮点数占4个字节。
        ByteBuffer buffer = ByteBuffer.allocateDirect(triangleCoordinates.length * 4);
        // 和本地平台保持一致的字节序
        buffer.order(ByteOrder.nativeOrder());
        // 将底层字节映射到FloatBuffer实例
        vertexBuffer = buffer.asFloatBuffer();
        // 将顶点拷贝到native内存中
        vertexBuffer.put(triangleCoordinates);
        // 每次put position都会+1，需要在绘制前重置为0
        vertexBuffer.position(0);

        // 加载着色器
        int vertexShader = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, context, R.raw.triangle_vert);
        int fragmentShader = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, context, R.raw.triangle_frag);
        // 加载程序
        program = ProgramLoader.loadProgram(vertexShader, fragmentShader);
    }

    public void draw() {
        // 添加程序
        GLES20.glUseProgram(program);

        // 获取顶点着色器中的顶点变量
        int positionHandle = GLES20.glGetAttribLocation(program, "a_Position");
        // 启用顶点参数
        GLES20.glEnableVertexAttribArray(positionHandle);
        // 准备顶点坐标数据
        GLES20.glVertexAttribPointer(positionHandle, COORDINATES_PER_VERTEX, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        // 获取片元着色器中的颜色变量
        int colorHandle = GLES20.glGetUniformLocation(program, "u_Color");
        // 设置颜色
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        // 绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // 禁用顶点参数
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

}
