package com.example.cyberseced;


import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.MainActivityViewHolder> {
    private ArrayList<Modules> modulesList;
    private RecyclerViewClickListener mListener;


    public LearningAdapter(ArrayList<Modules> modules, RecyclerViewClickListener listener) {
        modulesList = modules;
        mListener = listener;
    }


    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_adapter, parent, false);
        return new MainActivityViewHolder(v, mListener);


    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        Modules modules = modulesList.get(position);
//        holder.moduleName.setText(modules.getName());

        int icon = holder.itemView.getContext().getResources().getIdentifier("module_" + modules.getmImage(), "drawable", "com.example.cyberseced");
        holder.moduleIcon.setImageResource(icon);

    }

    @Override
    public int getItemCount() {
        return modulesList.size();
    }

    public class MainActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView moduleIcon;
        public TextView moduleName;
        private RecyclerViewClickListener mListener;

        public MainActivityViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            moduleIcon = itemView.findViewById(R.id.topicPicList);
         //   moduleName = itemView.findViewById(R.id.topicNameList);
            mListener = listener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
            }




    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }
}

