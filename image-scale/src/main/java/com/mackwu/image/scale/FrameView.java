package com.mackwu.image.scale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.mackwu.image.scale.util.MatrixUtil;
import com.mackwu.image.scale.util.ScaleLogger;

/**
 * @author MackWu
 * @since 2023/9/9 11:41
 */
public class FrameView extends AppCompatImageView {

    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    public FrameView(Context context) {
        this(context, null);
    }

    public FrameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private static final float SCALE_MIN = 1.0f;
    private static final float SCALE_MAX = 3.0f;
    private final Matrix scaleMatrix = new Matrix();
    private int pendingScaleType;
    private FrameMatrixConverter frameMatrixConverter;
    private float lastFocusX;
    private float lastFocusY;
    private float mTouchSlop;
    private boolean isScaling;
    private FrameMatrixChangedListener frameMatrixChangedListener;

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        // 矩阵类型
        super.setScaleType(ScaleType.MATRIX);
        //
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        //
        frameMatrixConverter = new FrameMatrixConverter(this);
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(@NonNull ScaleGestureDetector detector) {
                float scale = frameMatrixConverter.getScale(scaleMatrix);
                float scaleFactor = detector.getScaleFactor();
                // 缩放范围控制
                if ((scale < SCALE_MAX && scaleFactor > 1.0f)
                        || (scale > SCALE_MIN && scaleFactor < 1.0f)) {
                    // 最大值最小值判断
                    if (scale * scaleFactor > SCALE_MAX) {
                        scaleFactor = SCALE_MAX / scale;
                    }
                    if (scale * scaleFactor < SCALE_MIN) {
                        scaleFactor = SCALE_MIN / scale;
                    }
                    ScaleLogger.d("onScale...  scale=" + scale + ", scaleFactor=" + scaleFactor);
                    scaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
                    updateScaleMatrix();
                    lastFocusX = detector.getFocusX();
                    lastFocusY = detector.getFocusY();
                }
                return true;
            }

            @Override
            public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
                ScaleLogger.d("onScaleBegin...  scaleFactor=" + detector.getScaleFactor() + ", " + frameMatrixConverter.getScale(scaleMatrix));
                return true;
            }

            @Override
            public void onScaleEnd(@NonNull ScaleGestureDetector detector) {
                ScaleLogger.d("onScaleEnd...  scaleFactor=" + detector.getScaleFactor() + ", " + frameMatrixConverter.getScale(scaleMatrix));
            }
        });
        //
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onDoubleTap(@NonNull MotionEvent e) {
                return super.onDoubleTap(e);
            }
        });
    }

    private float downX, downY;
    private float focusX, focusY;
    private boolean isDragging;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        processTouchEvent(event);
        return true;
    }

    private void processTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                ScaleLogger.d("ACTION_DOWN...  "
                        + "downX=" + downX
                        + ", downY=" + downY
                );
                break;
            case MotionEvent.ACTION_MOVE:
//                float x = event.getX();
//                float y = event.getY();
//                float dx = x - downX;
//                float dy = y - downY;
//                if (!isDragging) {
//                    isDragging = Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
//                }
//                if (isDragging) {
//                    scaleMatrix.postTranslate(dx, dy);
//                    updateScaleMatrix();
//                    downX = x;
//                    downY = y;
//                    Logger.d("ACTION_MOVE...  "
//                            + "downX=" + downX
//                            + ", downY=" + downY
//                    );
//                }
                if (event.getPointerCount() == 2) {
                    float fx = (event.getX(0) + event.getX(1)) / 2;
                    float fy = (event.getY(0) + event.getY(1)) / 2;
                    float dx = fx - focusX;
                    float dy = fy - focusY;
                    if (!isDragging) {
                        isDragging = Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
                    }
                    if (isDragging) {
                        scaleMatrix.postTranslate(dx, dy);
                        updateScaleMatrix();
                        focusX = fx;
                        focusY = fy;
                        ScaleLogger.d("ACTION_MOVE...  " + ", focusX=" + focusX + ", focusY=" + focusY);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    focusX = (event.getX(0) + event.getX(1)) / 2;
                    focusY = (event.getY(0) + event.getY(1)) / 2;
                    ScaleLogger.d("ACTION_POINTER_DOWN...  " + ", focusX=" + focusX + ", focusY=" + focusY);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ScaleLogger.d(String.format("ACTION_POINTER_UP...  (%s, %s, %s)", MatrixUtil.getScale(scaleMatrix), MatrixUtil.getTransX(scaleMatrix), MatrixUtil.getTransY(scaleMatrix)));
                if (frameMatrixChangedListener != null) {
                    frameMatrixChangedListener.onMatrixChangeCompleted(scaleMatrix);
                }
                break;
        }
    }

    /**
     * use {@link #setPendingScaleType} instead
     *
     * @param scaleType The desired scaling mode.
     */
    @Override
    public void setScaleType(ScaleType scaleType) {
    }

    public void setPendingScaleType(int pendingScaleType) {
        this.pendingScaleType = pendingScaleType;
    }

    public int getPendingScaleType() {
        return pendingScaleType;
    }

    public void initScaleMatrix() {
        scaleMatrix.reset();
        Matrix scaleTypeMatrix = frameMatrixConverter.getScaleTypeMatrix(pendingScaleType);
        setImageMatrix(scaleTypeMatrix);
    }

    private void updateScaleMatrix() {
        frameMatrixConverter.checkMatrixBounds(scaleMatrix);
        setImageMatrix(scaleMatrix);
    }

    public void updateScaleMatrix(Matrix matrix) {
        scaleMatrix.set(matrix);
        updateScaleMatrix();
    }

    public void setFrameMatrixChangedListener(FrameMatrixChangedListener frameMatrixChangedListener) {
        this.frameMatrixChangedListener = frameMatrixChangedListener;
    }

}
