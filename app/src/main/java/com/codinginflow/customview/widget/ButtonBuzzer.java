package com.codinginflow.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CompoundButton;

import com.codinginflow.customview.R;

public class ButtonBuzzer extends CompoundButton {

    private int buzzerColor;
    private int stroke_buzzerColor;
    private int width;
    private int height;
    private Path path;
    private Paint paint;

    public ButtonBuzzer(Context context) {
        super(context);
    }

    public ButtonBuzzer(Context context, AttributeSet attrs) {
        super(context, attrs);
        GetAttributes(attrs);

        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void GetAttributes(AttributeSet attrs) {
        TypedArray a = getContext()
                .getTheme()
                .obtainStyledAttributes(attrs , R.styleable.ButtonBuzzer ,0, 0);

        try {
            buzzerColor = a.getColor(R.styleable.ButtonBuzzer_buzzerColor, Color.RED);
            stroke_buzzerColor = a.getColor(R.styleable.ButtonBuzzer_stroke_buzzerColor, Color.GRAY);
        } finally {
            a.recycle();
        }
    }

    public ButtonBuzzer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ButtonBuzzer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getBuzzerColor() {
        return buzzerColor;
    }

    public void setBuzzerColor(int buzzerColor) {
        this.buzzerColor = buzzerColor;
        invalidate();
        requestLayout();
    }

    public int getStroke_buzzerColor() {
        return stroke_buzzerColor;
    }

    public void setStroke_buzzerColor(int stroke_buzzerColor) {
        this.stroke_buzzerColor = stroke_buzzerColor;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = this.getWidth();
        height = this.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = this.getWidth();
        height = this.getHeight();

        float space = (Math.min(width, height) / 10);

        float TL2x = (width/10) + space;
        float TL2y = space/2;

        float TL1x = space/2;
        float TL1y = (height/10) + space;

        float BL1x = TL1x;
        float BL1y = height - TL1y;

        float BL2x = TL2x;
        float BL2y = height - TL2y;

        float BR1x = width - BL2x;
        float BR1y = BL2y;

        float BR2x = width - BL1x;
        float BR2y = BL1y;

        float TR1x = BR2x;
        float TR1y = TL1y;

        float TR2x = BR1x;
        float TR2y = TL2y;

        path.moveTo(TL2x, TL2y);
        path.lineTo(TL1x, TL1y);
        path.lineTo((width/2)-space, height/2);
        path.lineTo(BL1x, BL1y);
        path.lineTo(BL2x, BL2y);
        path.lineTo(width/2, (height/2)+space);
        path.lineTo(BR1x, BR1y);
        path.lineTo(BR2x, BR2y);
        path.lineTo((width/2)+space, height/2);
        path.lineTo(TR1x, TR1y);
        path.lineTo(TR2x, TR2y);
        path.lineTo(width/2, (height/2)-space);
        path.lineTo(TL2x-space/9, TL2y-space/9);

        paint.setColor(buzzerColor);

        if (this.isChecked()){
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, paint);
        }

        paint.setColor(stroke_buzzerColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(space/3);
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP)
            setChecked(!this.isChecked());
        invalidate();
        requestLayout();
        return true;
    }
}
