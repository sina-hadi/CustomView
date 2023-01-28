/**
 * Copyright (c) 2013 Wireless Designs, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.codinginflow.customview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.codinginflow.customview.widget.EntryFormView;

public class CompoundControlActivity extends Activity implements EntryFormView.OnEntrySubmittedListener{

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