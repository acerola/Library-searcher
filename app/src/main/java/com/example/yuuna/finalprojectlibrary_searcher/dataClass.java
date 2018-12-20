package com.example.yuuna.finalprojectlibrary_searcher;

import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

public class dataClass {


    static public class searchData
    {
        private String searchAll;
        private String writer;
        private String bookName;
        private String dbname;
        private int startYear;
        private int endYear;
        private int isThereOriginal;

        public searchData(String all, String wr , String bn ,String db, int start , int end, int is)
        {
            super();
            searchAll = all;
            writer = wr;
            bookName = bn;
            dbname = db;
            startYear = start;
            endYear = end;
            isThereOriginal = is;
        }

        public String getSearchAll()
        {
            return searchAll;
        }

        public String getWriter()
        {
            return writer;
        }

        public String getBookName()
        {
            return bookName;
        }

        public String getDbname()
        {
            return dbname;
        }

        public int getStartYear()
        {
            return startYear;
        }

        public int getEndYear()
        {
            return endYear;
        }

        public int getIsThereOriginal()
        {
            return isThereOriginal;
        }



    }



}
