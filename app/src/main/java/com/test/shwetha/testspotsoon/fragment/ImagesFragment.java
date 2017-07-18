package com.test.shwetha.testspotsoon.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.shwetha.testspotsoon.R;
import com.test.shwetha.testspotsoon.adapters.MyTabCustomAdapter;
import com.test.shwetha.testspotsoon.model.MyModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<MyModel> mySongList = new ArrayList<>();
    private MyTabCustomAdapter mAdapter;

    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_videos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyTabCustomAdapter(mySongList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareMovieData();
        return view;
    }
    private void prepareMovieData() {

        MyModel myModel = new MyModel(R.mipmap.chainsmokerbg, getResources().getString(R.string.title1),
                getResources().getString(R.string.time1), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        myModel = new MyModel(R.mipmap.chainsmoker3_bg, getResources().getString(R.string.title2),
                getResources().getString(R.string.time2), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        myModel = new MyModel(R.mipmap.chainsmoker2_bg, getResources().getString(R.string.title3),
                getResources().getString(R.string.time3), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        myModel = new MyModel(R.mipmap.chainsmoker3_bg, getResources().getString(R.string.title2),
                getResources().getString(R.string.time3), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        myModel = new MyModel(R.mipmap.chainsmokerbg, getResources().getString(R.string.title3),
                getResources().getString(R.string.time3), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        myModel = new MyModel(R.mipmap.chainsmoker2_bg, getResources().getString(R.string.title1),
                getResources().getString(R.string.time3), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        myModel = new MyModel(R.mipmap.chainsmokerbg, getResources().getString(R.string.title2),
                getResources().getString(R.string.time3), getResources().getString(R.string.desc_text));
        mySongList.add(myModel);

        mAdapter.notifyDataSetChanged();
    }
}
