package com.example.yuuna.finalprojectlibrary_searcher;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class theSearcher extends AsyncTask<Integer,Integer,ArrayList<LinkedHashMap<String,String>>> {
    private dataClass.searchData sData;
    private int searchPagenum;
    private int searchNum;
    public ArrayList<LinkedHashMap<String,String>> searchResult;

    public theSearcher(dataClass.searchData data, int pagenum , int num )
    {
        super();
        sData = data;
        searchPagenum = pagenum;
        searchNum = num;
    }


    protected ArrayList<LinkedHashMap<String,String>> doInBackground(Integer... searchType){
        XmlParser xml = new XmlParser();
        if(searchType[0] == 0)
        {
            searchFragment.Lastresult = xml.basicSearch(sData.getSearchAll(),sData.getBookName(),sData.getWriter(),searchPagenum,searchNum);
        }
        else
        {
            searchFragment.Lastresult = xml.basicSearch(sData.getSearchAll(),sData.getBookName(),sData.getWriter(),searchPagenum,searchNum);
        }
        return searchFragment.Lastresult;
    }

    @Override
    protected void onPostExecute(ArrayList<LinkedHashMap<String,String>> result)
    {
        searchResult = result;
        searchFragment.isDone = true;
    }
}
