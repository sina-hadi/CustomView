package com.codinginflow.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.CompoundButton;

public class ButtonBuzzer extends CompoundButton {

    private int buzzerColor;
    private int stroke_buzzerColor;

    public ButtonBuzzer(Context context) {
        super(context);
    }

    public ButtonBuzzer(Context context, AttributeSet attrs) {
        super(context, attrs);
        GetAttributes(attrs);

    }

    private void GetAttributes(AttributeSet attrs) {
        TypedArray a = getContext()
                .getTheme()
                .obtainStyledAttributes(attrs ,R.styleable.ButtonBuzzer ,0, 0);

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
    }

    public int getStroke_buzzerColor() {
        return stroke_buzzerColor;
    }

    public void setStroke_buzzerColor(int stroke_buzzerColor) {
        this.stroke_buzzerColor = stroke_buzzerColor;
    }
}
