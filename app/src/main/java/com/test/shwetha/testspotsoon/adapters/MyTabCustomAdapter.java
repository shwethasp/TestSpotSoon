package com.test.shwetha.testspotsoon.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.shwetha.testspotsoon.R;
import com.test.shwetha.testspotsoon.model.MyModel;

import java.util.ArrayList;

/**
 * Created by shwetha on 18/07/17.
 */

public class MyTabCustomAdapter extends RecyclerView.Adapter<MyTabCustomAdapter.MyViewHolder> {

    ArrayList<MyModel> mySongList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView musicImage;
        public TextView title, subTitle, description;

        public MyViewHolder(View view) {
            super(view);
            musicImage =(ImageView)view.findViewById(R.id.music_image);
            title = (TextView) view.findViewById(R.id.title);
            subTitle = (TextView) view.findViewById(R.id.subTitle);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    public MyTabCustomAdapter(ArrayList<MyModel> mySongList) {
        this.mySongList = mySongList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyModel model = mySongList.get(position);
        holder.musicImage.setImageResource(model.getMusicImage());
        holder.title.setText(model.getHeading());
        holder.subTitle.setText(model.getSubHeading());
        holder.description.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return mySongList.size();
    }

}
