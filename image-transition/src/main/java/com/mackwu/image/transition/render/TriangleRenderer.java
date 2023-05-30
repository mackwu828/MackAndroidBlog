package com.mackwu.image.transition.render;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.mackwu.image.transition.Triangle;
import com.mackwu.image.transition.util.Logger;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author MackWu
 * @since 2023/3/4 17:07
 */
public class TriangleRenderer implements GLSurfaceView.Renderer {

    private final Context context;
    private Triangle triangle;


    public TriangleRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Logger.d("onSurfaceCreated...");
        // 设置清屏颜色
        GLES20.glClearColor(0.9f, 0.9f, 0.9f, 1f);
        triangle = new Triangle(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Logger.d("onSurfaceChanged...");
        // 设置控件区域。如区域时整个GLSurfaceView
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Logger.d("onDrawFrame...");
        // 绘制清屏颜色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        triangle.draw();
    }

}
