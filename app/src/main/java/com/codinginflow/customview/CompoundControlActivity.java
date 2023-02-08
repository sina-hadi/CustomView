package com.codinginflow.customview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.codinginflow.customview.widget.EntryFormView;

public class CompoundControlActivity extends Activity implements EntryFormView.OnEntrySubmittedListener {

    private ArrayAdapter<String> mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compound);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(mAdapter);

        EntryFormView entryView = (EntryFormView) findViewById(R.id.entry_view);
        entryView.setOnEntrySubmittedListener(this);
    }

    @Override
    public void onEntrySubmitted(CharSequence name, CharSequence email) {
        mAdapter.add(name + ", " + email);
        mAdapter.notifyDataSetChanged();
    }
}