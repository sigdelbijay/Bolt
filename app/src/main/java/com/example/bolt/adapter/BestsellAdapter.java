package com.example.bolt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolt.R;
import com.example.bolt.domain.Bestsell;

import java.util.List;

public class BestsellAdapter extends RecyclerView.Adapter<BestsellAdapter.ViewHolder> {

    Context context;
    List<Bestsell> mBestsellList;
    public BestsellAdapter(Context context, List<Bestsell> mBestsellList) {
        this.context = context;
        this.mBestsellList = mBestsellList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_feature_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBestsellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mFeatureImg;
        private TextView mFTxtCost, mFTxtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFeatureImg = itemView.findViewById(R.id.feature_img);
            mFTxtCost = itemView.findViewById(R.id.ftxt_cost);
            mFTxtName = itemView.findViewById(R.id.ftxt_name);
        }
    }
}
