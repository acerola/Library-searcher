package com.example.yuuna.finalprojectlibrary_searcher;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.design.widget.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class detailedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    final static String ARG_POSITION = "position";

    private LinkedHashMap<String, String> data;
    private ArrayList<String[]> decodedData;

    private View mainFragment;
    private Button contentButton;
    private Button bookmarkButton;
    private ListView listView;
    private SimpleAdapter adapter;

    // TODO: Rename and change types of parameters
    private int position;

    detailedListener mCallback;

    public detailedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailedFragment newInstance(String param1, String param2) {
        detailedFragment fragment = new detailedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }

        data = mCallback.onFragmentInteraction(position);
        Set set = data.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<String[]> result = new ArrayList<>();
        while(iterator.hasNext()) {
            String[] t = new String[2];
            Map.Entry item = (Map.Entry) iterator.next();
            t[0] = ((String)item.getKey());
            t[1] = ((String) item.getValue());
            result.add(t);
        }
        decodedData = result;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainFragment =  inflater.inflate(R.layout.fragment_detailed, container, false);
        contentButton = (Button)mainFragment.findViewById(R.id.contentButton);
        bookmarkButton = (Button)mainFragment.findViewById(R.id.bookmarkButton);
        listView = (ListView) mainFragment.findViewById(R.id.detail_list);

        adapter= new SimpleAdapter(decodedData,getActivity().getApplicationContext());
        listView.setAdapter(adapter);

        contentButton.setOnClickListener(new detailedFragment.ContentButtonClickListener());
        bookmarkButton.setOnClickListener(new detailedFragment.BookmarkButtonClickListener());

        return mainFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof detailedListener) {
            mCallback = (detailedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement detailedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public interface detailedListener {
        LinkedHashMap<String,String> onFragmentInteraction(int position);
    }

    class ContentButtonClickListener implements  View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Snackbar.make(v, "현재 목차보기 API가 작동하지 않으므로 작동할 수 없습니다.", Snackbar.LENGTH_LONG)
                    .setAction("No action", null).show();
        }
    }

    class BookmarkButtonClickListener implements  View.OnClickListener
    {
        @Override
        public void onClick(View V)
        {

        }
    }
}
