package com.codinginflow.customview;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import com.codinginflow.customview.widget.Shapes;

public class ShapesActivity extends AppCompatActivity {

    private Shapes mShape;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shapes);

        mShape = (Shapes) findViewById(R.id.shapeCustomView);

        findViewById(R.id.swapButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShape.swapColor();
            }
        });

    }
}