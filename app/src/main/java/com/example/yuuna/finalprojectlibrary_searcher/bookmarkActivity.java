package com.example.yuuna.finalprojectlibrary_searcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class bookmarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        ListView list = (ListView)findViewById(R.id.bookmarkList);
        list.setEmptyView((TextView)findViewById(R.id.DelListempty));
    }
}
