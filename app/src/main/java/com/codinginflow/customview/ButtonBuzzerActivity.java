package com.codinginflow.customview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.codinginflow.customview.widget.ButtonBuzzer;

public class ButtonBuzzerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_buzzer);

        ButtonBuzzer BT = findViewById(R.id.buttonBuzzer);

        BT.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (BT.isChecked())
                Toast.makeText(this, "State is True", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "State is False", Toast.LENGTH_SHORT).show();
        });

    }
}