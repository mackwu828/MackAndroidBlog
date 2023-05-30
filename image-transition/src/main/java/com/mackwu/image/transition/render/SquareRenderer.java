package com.mackwu.image.transition.render;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.mackwu.image.transition.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author MackWu
 * @since 2023/3/4 17:07
 */
public class SquareRenderer implements GLSurfaceView.Renderer {

    private final Context context;
    private Square square;

    public SquareRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.9f, 0.9f, 0.9f, 1f);
        square = new Square(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        square.draw();
    }

}
