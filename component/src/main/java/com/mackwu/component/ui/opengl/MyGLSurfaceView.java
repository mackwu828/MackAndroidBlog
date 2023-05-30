package com.mackwu.component.ui.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.mackwu.image.transition.render.TriangleRenderer;

/**
 * @author MackWu
 * @since 2023/3/3 15:38
 */
public class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Context context) {
        this(context, null);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        // 设置OpenGLES版本。设置为OpenGLES2.0
        setEGLContextClientVersion(2);
        // 设置渲染器
        setRenderer(new TriangleRenderer(getContext()));
        // 设置渲染器模式。
        // RENDERMODE_WHEN_DIRTY 当调用requestRender()才渲染控件，防止onDrawFrame一直回调。
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

}
