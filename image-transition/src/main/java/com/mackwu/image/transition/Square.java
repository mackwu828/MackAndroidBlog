package com.mackwu.image.transition;

import android.content.Context;
import android.opengl.GLES20;

import com.mackwu.image.transition.util.ProgramLoader;
import com.mackwu.image.transition.util.ShaderLoader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * @author MackWu
 * @since 2023/3/4 16:40
 */
public class Square {


    // 正方形的坐标
    private static final float[] squareCoordinates = {
            -0.5f, 0.5f,
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,
    };
    // 每个顶点的坐标数
    private static final int COORDINATES_PER_VERTEX = 2;
    // 顶点缓存
    private final FloatBuffer vertexBuffer;
    // 顶点数量
    private final int vertexCount = squareCoordinates.length / COORDINATES_PER_VERTEX;
    // 顶点绘制顺序缓存。
    private final ShortBuffer drawListBuffer;
    // 顶点绘制顺序。按逆时针绘制顶点。
    // 0代表第一个顶点。如-0.5f, 0.5f
    // 即先绘制左下角的三角形，再绘制右上角的三角形。
    private final short[] drawOrder = {0, 1, 2, 0, 2, 3};

    // 程序
    private final int program;
    // 颜色。rgb和alpha
    private final float[] color = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Square(Context context) {
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoordinates.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoordinates);
        vertexBuffer.position(0);

        // 分配native内存，每个整数占2个字节。
        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

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
        // 准备颜色数据
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        // 绘制正方形
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // 禁用顶点参数
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

}
