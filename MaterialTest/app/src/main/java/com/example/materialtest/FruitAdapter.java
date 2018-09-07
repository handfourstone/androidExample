package com.example.materialtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;

    private List<Fruits> mFruitsList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            fruitImage = view.findViewById(R.id.fruit_iamge);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruits> fruitsList) {
        mFruitsList = fruitsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruits fruit = mFruitsList.get(i);
        viewHolder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(viewHolder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitsList.size();
    }
}
