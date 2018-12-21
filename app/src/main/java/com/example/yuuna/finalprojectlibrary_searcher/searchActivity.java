package com.example.yuuna.finalprojectlibrary_searcher;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class searchActivity extends AppCompatActivity implements searchFragment.SearchListener , detailedFragment.detailedListener {

    ArrayList<LinkedHashMap<String,String>> interactionData;

    //TODO : don't create view twice
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
    public void onSelectedItem(ArrayList<LinkedHashMap<String,String>> data , int position)
    {
        detailedFragment secondFragment = new detailedFragment();
        Bundle args = new Bundle();
        args.putInt(searchFragment.ARG_POSITION,position);
        secondFragment.setArguments(args);
        interactionData = data;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public LinkedHashMap<String,String> onFragmentInteraction(int position)
    {
        return interactionData.get(position);
    }
}
