package com.example.administrator.bottomnavigationbardemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {


    public TvFragment() {
        // Required empty public constructor
//        Log.i("tag","TvFragment被初始化了");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("tag","TvFragment被初始化了");
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    public static TvFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        TvFragment fragment = new TvFragment();
        fragment.setArguments(args);
        return fragment;

    }


}
