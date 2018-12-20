package com.example.yuuna.finalprojectlibrary_searcher;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class theSearcher extends AsyncTask<Integer,Integer,ArrayList<HashMap<String,String>>> {
    private dataClass.searchData sData;
    private int searchPagenum;
    private int searchNum;
    public ArrayList<HashMap<String,String>> searchResult;

    public theSearcher(dataClass.searchData data, int pagenum , int num , ArrayList<HashMap<String,String>> result )
    {
        super();
        sData = data;
        searchPagenum = pagenum;
        searchNum = num;
        searchResult = result;
    }


    protected ArrayList<HashMap<String,String>> doInBackground(Integer... searchType){
        XmlParser xml = new XmlParser();
        if(searchType[0] == 0)
        {
            searchResult = xml.basicSearch(sData.getSearchAll(),sData.getBookName(),sData.getWriter(),searchPagenum,searchNum);
        }
        else
        {
            searchResult = xml.basicSearch(sData.getSearchAll(),sData.getBookName(),sData.getWriter(),searchPagenum,searchNum);
        }
        return searchResult;
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String,String>> result)
    {
        searchResult = result;
    }
}
