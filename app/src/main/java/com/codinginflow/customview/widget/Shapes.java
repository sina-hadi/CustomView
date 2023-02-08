package com.codinginflow.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import com.codinginflow.customview.R;

import java.util.Timer;
import java.util.TimerTask;

public class Shapes extends View {

    private static final int SQUARE_SIZE = 300;
    private Rect mRectSquare;
    private Paint mPaintSquare;

    private RectF indicator1;
    private RectF indicator2;
    private Paint mPaintIndicator;

    private int mSquareColor;
    private int mSquareSize;

    private Paint mPaintCircle;
    private float radius = 100f;
    private float cx, cy;

    private Bitmap mImage;

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
        indicator1 = new RectF();
        indicator2 = new RectF();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.Shapes);
        mSquareColor = ta.getColor(R.styleable.Shapes_square_color, Color.GREEN);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.Shapes_square_size, SQUARE_SIZE);
        swapColor();
        ta.recycle();

        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.rick_morty);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                int padding = 50;
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mImage = getResizedBitmap(mImage, getWidth() - padding, getHeight() - padding);

                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        int newWidth = mImage.getWidth() - 10;
                        int newHeight = mImage.getHeight() - 10;

                        if (newWidth <= 0 || newHeight <= 0) {
                            cancel();
                            return;
                        }

                        mImage = getResizedBitmap(mImage, newWidth, newHeight);
                        postInvalidate();
                    }
                }, 5001, 200);
            }
        });

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.parseColor("#00ccff"));

        mPaintIndicator = new Paint();
        mPaintIndicator.setAntiAlias(true);
        mPaintIndicator.setColor(Color.parseColor("#FFFFFF"));

    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int width, int height) {
        Matrix matrix = new Matrix();

        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, width, height);

        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(bitmap, 0, 0 ,bitmap.getWidth(), bitmap.getHeight(), matrix, true);

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

        indicator1.left = 500;
        indicator1.top = 800;
        indicator1.right = indicator1.left + 400;
        indicator1.bottom = indicator1.top + 400;

        indicator2.left = 550;
        indicator2.top = 850;
        indicator2.right = indicator2.left + 300;
        indicator2.bottom = indicator2.top + 300;


        canvas.drawRect(mRectSquare, mPaintSquare);

        if (cy == 0f || cx == 0f) {
            cx = getWidth() - radius - 50f;
            cy = mRectSquare.top + (mRectSquare.height() / 2);
        }
        canvas.drawCircle(cx, cy, radius, mPaintCircle);

        canvas.drawArc(indicator1, 180, 180, true,mPaintCircle);
        canvas.drawArc(indicator2, 180, 180, true,mPaintIndicator);

        float imageX = (getWidth() - mImage.getWidth()) / 2;
        float imageY = (getHeight() - mImage.getHeight()) / 2;

        canvas.drawBitmap(mImage, imageX, imageY, null);

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

