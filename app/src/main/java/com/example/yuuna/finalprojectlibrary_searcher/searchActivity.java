package com.example.yuuna.finalprojectlibrary_searcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class searchActivity extends AppCompatActivity implements searchFragment.SearchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if(findViewById(R.id.fragment_container) != null){
            searchFragment firstFragment = new searchFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment).commit();
        }
    }

    @Override
    public void onWordSelected(int position)
    {

    }
}
