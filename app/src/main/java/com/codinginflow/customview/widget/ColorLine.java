package com.codinginflow.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ColorLine extends View {

    private int color;
    private int halfWidth;
    private Rect rect;
    private Paint paint;

    public ColorLine(Context context) {
        super(context);
        init();
    }

    public ColorLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ColorLine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        color = Color.BLUE;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int givenHeight = MeasureSpec.getSize(heightMeasureSpec);
        int givenWidth = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(givenWidth, givenHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.getClipBounds(rect);

        halfWidth = rect.width() / 2;
        rect.right = rect.right - halfWidth;

        canvas.drawRect(rect, paint);

        paint.setColor(Color.RED);

        canvas.getClipBounds(rect);
        rect.left = rect.left + halfWidth;
        canvas.drawRect(rect, paint);

    }
}
