package com.codinginflow.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final String[] ITEMS = {
            "AspectImageView",
            "Compound Control",
            "DoubleImageView",
            "BoxGridLayout",
            "ButtonBuzzer",
            "ColorLine",
            "Shapes"
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ITEMS);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        setContentView(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: {
                Intent i = new Intent(this, AspectImageActivity.class);
                startActivity(i);
                break;
            }
            case 1: {
                Intent i = new Intent(this, CompoundControlActivity.class);
                startActivity(i);
                break;
            }
            case 2: {
                Intent i = new Intent(this, DoubleImageActivity.class);
                startActivity(i);
                break;
            }
            case 3: {
                Intent i = new Intent(this, BoxGridActivity.class);
                startActivity(i);
                break;
            }
            case 4: {
                Intent i = new Intent(this, ButtonBuzzerActivity.class);
                startActivity(i);
                break;
            }
            case 5: {
                Intent i = new Intent(this, ColorLineActivity.class);
                startActivity(i);
                break;
            }
            case 6: {
                Intent i = new Intent(this, ShapesActivity.class);
                startActivity(i);
                break;
            }
            default:
                break;
        }
    }
}