package com.example.cyberseced;


import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {
    private ArrayList<Modules> mmList;
    private OnMainAdapterListener mMainAdapterListener;


    public MainActivityAdapter(ArrayList<Modules> mList, OnMainAdapterListener onMainAdapterListener) {
        mmList = mList;
        this.mMainAdapterListener = onMainAdapterListener;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_adapter, parent, false);
       return  new MainActivityViewHolder(v,mMainAdapterListener);


    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        Modules cModules = mmList.get(position);

        holder.mIcon.setImageResource(cModules.getmImage());
        holder.mLabel.setText(cModules.getName());

    }

    @Override
    public int getItemCount() {
        return mmList.size();
    }

    public class MainActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mIcon;
        public TextView mLabel;
        OnMainAdapterListener onMainAdapterListener;

        public MainActivityViewHolder(@NonNull View itemView, OnMainAdapterListener onMainAdapterListener) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.topicPicList);
            mLabel = itemView.findViewById(R.id.topicNameList);
          this.onMainAdapterListener = onMainAdapterListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onMainAdapterListener.OnMainAdapterClick(getAdapterPosition());
        }
    }

    public interface OnMainAdapterListener{
        void OnMainAdapterClick (int position);
    }
}

