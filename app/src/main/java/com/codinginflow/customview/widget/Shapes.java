package com.codinginflow.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.codinginflow.customview.R;

public class Shapes extends View {

    private static final int SQUARE_SIZE = 300;
    private Rect mRectSquare;
    private Paint mPaintSquare;

    private int mSquareColor;
    private int mSquareSize;

    private Paint mPaintCircle;
    private float radius = 100f;
    private float cx, cy;

    private boolean state = false;

    public Shapes(Context context) {
        super(context);
        init(null);
    }

    public Shapes(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Shapes(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Shapes(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set) {

        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.Shapes);
        mSquareColor = ta.getColor(R.styleable.Shapes_square_color, Color.GREEN);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.Shapes_square_size, SQUARE_SIZE);
        swapColor();
        ta.recycle();

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.parseColor("#00ccff"));

    }

    public void swapColor() {
        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor ? Color.RED : mSquareColor);
        state = !state;

        // Won't block UI
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.right = mRectSquare.left + mSquareSize;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;

        canvas.drawRect(mRectSquare, mPaintSquare);

        if (cy == 0f || cx == 0f) {
            cx = getWidth() - radius - 50f;
            cy = mRectSquare.top + (mRectSquare.height() / 2);
        }
        canvas.drawCircle(cx, cy, radius, mPaintCircle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();

                if (mRectSquare.left < x && mRectSquare.right > x) {
                    if (mRectSquare.top < y && mRectSquare.bottom > y) {
                        if (state) {
                            radius += 10f;
                        } else {
                            radius -= 10f;
                        }
                        postInvalidate();
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();

                double dx = Math.pow(x - cx, 2);
                double dy = Math.pow(y - cy, 2);

                if (dx + dy < Math.pow(radius, 2)) {
                    cx = x;
                    cy = y;
                    postInvalidate();

                    return true;
                }
                return value;
            }
        }
        return value;
    }
}

