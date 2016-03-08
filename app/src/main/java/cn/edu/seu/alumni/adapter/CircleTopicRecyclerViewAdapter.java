package cn.edu.seu.alumni.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import cn.edu.seu.alumni.R;

/**
 * Created by Dell on 2016/3/8.
 */
public class CircleTopicRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_topic_dynamic, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
