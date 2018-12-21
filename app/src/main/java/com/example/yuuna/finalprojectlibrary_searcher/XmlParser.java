package com.example.yuuna.finalprojectlibrary_searcher;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class XmlParser {

    private final static String basicSearch = "https://openapi.nanet.go.kr/nadl/rest/searchservice/basic?serviceKey=";
    private final static String detailSearch = "https://openapi.nanet.go.kr/nadl/rest/searchservice/detail?serviceKey=";
    private final static String contentSearch = "https://openapi.nanet.go.kr/nadl/rest/detailinfoservice/toc?serviceKey=";
    private final static String serviceKey = "0xe7U7sjm%2BSyQJr75s4oEYs4Jqo9EvG4XaJY8%2FhXQXQUF42ooBRJ78js8rfIqBMt3YQX79fay6Z4ai3qVGuHLg%3D%3D";

    public ArrayList<LinkedHashMap<String,String>> basicSearch(String all , String name , String writer , int pagenum , int num) {
        String requestUrl = basicSearch;
        requestUrl += serviceKey;
        requestUrl += "&pageno=" + pagenum;
        requestUrl += "&displaylines=" + num;
        requestUrl += "&search=";

        ArrayList<LinkedHashMap<String,String>> result = null;

        if (!all.matches("")) {
            requestUrl += "전체," + all;
            if (!name.matches("")  || !writer.matches("")) {
                requestUrl += "|";
            }
        }

        if (!name.matches("")) {
            requestUrl += "서명," + name;
            if (!writer.matches("")) {
                requestUrl += "|";
            }
        }

        if (!writer.matches("")){
            requestUrl += "저자명," + writer;
        }

        URL url;

        try {
            String nameString = null;
            String valueString = null;
            boolean nameBoolean= false;
            boolean valueBoolean= false;

            url = new URL(requestUrl);
            InputStream is = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(is, "UTF-8"));

            int eventType = parser.getEventType();
            LinkedHashMap<String,String> data = null;
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        result = new ArrayList<>();
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("record") && data != null) {
                            result.add(data);
                            data = null;
                        }
                        if(parser.getName().equals("item"))
                        {
                            data.put(nameString,valueString);
                            nameString = null;
                            valueString = null;
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("record")){
                            data = new LinkedHashMap<>();
                        }
                        if(parser.getName().equals("name"))
                        {
                            nameBoolean = true;
                        }
                        if(parser.getName().equals("value"))
                        {
                            valueBoolean = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(nameBoolean)
                        {
                            nameString = parser.getText();
                            nameBoolean = false;
                        }else if(valueBoolean)
                        {
                            valueString = parser.getText();
                            valueBoolean = false;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<LinkedHashMap<String,String>> detailSearch(String dbname , String all , String name , String writer ,int startYear, int endYear , int isThere ,  int pagenum , int num) {
        String requestUrl = detailSearch;
        requestUrl += serviceKey;
        requestUrl += "&dbname=" + dbname;
        requestUrl += "&pageno=" + pagenum;
        requestUrl += "&displaylines=" + num;
        requestUrl += "&search=";

        ArrayList<LinkedHashMap<String,String>> result = null;

        if (all != null) {
            requestUrl += "전체," + all;
            if (name != null || writer != null) {
                requestUrl += "|";
            }
        }

        if (name != null) {
            requestUrl += "서명," + name;
            if (writer != null) {
                requestUrl += "|";
            }
        }

        if (writer != null){
            requestUrl += "저자명," + writer;
        }

        // TODO: start , end year , isThere

        URL url;

        try {
            url = new URL(requestUrl);
            InputStream is = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(is, "UTF-8"));

            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                LinkedHashMap<String,String> data = null;
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        result = new ArrayList<>();
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("record") && data != null) {
                            result.add(data);
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("record")){
                            data = new LinkedHashMap<>();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        data.put(parser.getName(), parser.getText());
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}