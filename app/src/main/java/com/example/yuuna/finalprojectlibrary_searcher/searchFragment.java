package com.example.yuuna.finalprojectlibrary_searcher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class searchFragment extends Fragment {
    final static String ARG_POSITION = "position";
    private static final int dataNum = 10;
    public static ArrayList<HashMap<String,String>> Lastresult;

    public static boolean isDone = false;

    SearchListener mCallback;

    private String searchAll;
    private String writer;
    private String bookName;
    private String dbname;
    private int startYear;
    private int endYear;
    private int isThereOriginal;

    public CustomAdapter adapter;

    private View mainFragment;
    ListView listView;

    private Button searchButton;
    private Button detailSearchButton;

    private SearchListener mListener;

    Handler handler;
    Thread thread;


    public searchFragment() {
        // Required empty public constructor
    }

    public void createList()
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter= new CustomAdapter(Lastresult,getActivity().getApplicationContext());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        mCallback.onSelectedItem(position);
                    }
                });

            }
        });
    }

    public interface SearchListener {
        void onSelectedItem(int position);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        handler = new Handler();
        thread = new Thread(new checker());
        thread.setDaemon(true);
        thread.start();

    }

    class checker implements Runnable {
        public void run(){
            int flag = 1;
            while(flag == 1)
            {
                if(isDone)
                {
                    createList();
                    isDone = false;
                    flag = 2;
                }

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainFragment =  inflater.inflate(R.layout.fragment_search, container, false);
        searchButton = (Button)mainFragment.findViewById(R.id.search_button);
        detailSearchButton = (Button)mainFragment.findViewById(R.id.search_detail_button);
        listView = (ListView) mainFragment.findViewById(R.id.listView);

        searchButton.setOnClickListener(new SearchButtonClickListener());
        detailSearchButton.setOnClickListener(new DetailSearchButtonClickListener());

        return mainFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchListener) {
            mListener = (SearchListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SearchListener");
        }

        mCallback = (SearchListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class SearchButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int searchType = 0;
            searchAll = ((EditText)mainFragment.findViewById(R.id.search_all)).getText().toString();
            writer = ((EditText)mainFragment.findViewById(R.id.search_writer)).getText().toString();
            bookName = ((EditText)mainFragment.findViewById(R.id.search_book_name)).getText().toString();

            dbname = ((TextView)mainFragment.findViewById(R.id.search_dataType)).getText().toString();
            if(!(((TextView)mainFragment.findViewById(R.id.search_start_year)).getText().toString().matches(""))) {
                startYear = Integer.getInteger(((TextView) mainFragment.findViewById(R.id.search_start_year)).getText().toString());
            }
            else
            {
                startYear = -1;
            }
            if(!(((TextView)mainFragment.findViewById(R.id.search_end_year)).getText().toString().matches(""))) {
                endYear = Integer.getInteger(((TextView) mainFragment.findViewById(R.id.search_end_year)).getText().toString());
            }
            else
            {
                endYear = -1;
            }
            if(!(((TextView)mainFragment.findViewById(R.id.search_isThere)).getText().toString().matches(""))) {
                isThereOriginal = ((TextView) mainFragment.findViewById(R.id.search_isThere)).getText().toString() == "Y" ? 1 : 0;
            }
            else
            {
                isThereOriginal = -1;
            }

            //input value test
            if(searchAll.matches("") && writer.matches("") && bookName.matches(""))
            {
                Toast.makeText(getActivity(),"적어도 전체/서명/저자명 중 하나는 입력해야합니다", Toast.LENGTH_LONG).show();
                return;
            }


            dataClass.searchData theSearchData = new dataClass.searchData(searchAll,writer,bookName,dbname,startYear,endYear,isThereOriginal);



            if(!dbname.matches("전체") || startYear >0 || endYear >0  || isThereOriginal != -1 )
            {
                Toast.makeText(getActivity(),"상세검색모드 발동", Toast.LENGTH_SHORT).show();
                searchType = 1;
            }

            if(searchType == 1)
            {
                if(dbname.matches("전체"))
                {
                    Toast.makeText(getActivity(),"상세검색을 위해선 자료 종류가 전체가 아니여야 합니다. ", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            theSearcher searcher = new theSearcher(theSearchData,1,dataNum);

            if(searchType == 0)
            {
                searcher.execute(0);
            }
            else
            {
                searcher.execute(1);
            }

            return;
        }
    }

    class DetailSearchButtonClickListener implements  View.OnClickListener
    {
        @Override
        public void onClick(View V)
        {
            LinearLayout dialogView = (LinearLayout)View.inflate(getActivity(),R.layout.dialog, null);
            new AlertDialog.Builder(getActivity())
                    .setTitle("상세설정")
                    .setView(dialogView)
                    .setPositiveButton("설정하기",new DialogButtonClickListener())
                    .setCancelable(true)
                    .show();
        }
    }

    class DialogButtonClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int button){

        }
    }



}
