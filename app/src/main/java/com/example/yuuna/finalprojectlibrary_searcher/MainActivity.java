package com.example.yuuna.finalprojectlibrary_searcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toCreditActivity(View v)
    {
        Intent i = new Intent(this, creditActivity.class);
        startActivity(i);
    }

    public void toSearchActivity(View v)
    {
        Intent i = new Intent(this, searchActivity.class);
        startActivity(i);
    }

    public void toBookMarkActivity(View V)
    {
        Intent i = new Intent(this, bookmarkActivity.class);
        startActivity(i);
    }

}
