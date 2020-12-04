package com.example.bolt.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bolt.DetailActivity;
import com.example.bolt.R;
import com.example.bolt.domain.Category;
import com.example.bolt.domain.Feature;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {

    Context context;
    List<Feature> mFeatureList;
    public FeatureAdapter(Context context, List<Feature> mFeatureList) {
        this.context = context;
        this.mFeatureList = mFeatureList;
    }

    @NonNull
    @Override
    public FeatureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_feature_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureAdapter.ViewHolder holder, int position) {
//        holder.mFeatureImg.
        holder.mFTxtName.setText(mFeatureList.get(position).getName());
        holder.mFTxtCost.setText(mFeatureList.get(position).getPrice()+ "$");
        Glide.with(context).load(mFeatureList.get(position).getImg_url()).into(holder.mFeatureImg);
        holder.mFeatureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("itemDetails", mFeatureList.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mFeatureList.size();
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
