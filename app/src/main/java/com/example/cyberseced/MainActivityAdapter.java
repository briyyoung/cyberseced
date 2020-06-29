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

    private ArrayList<Topic> mTopics;
    private RecyclerViewClickListener mListener;

    public MainActivityAdapter(ArrayList<Topic> topics, RecyclerViewClickListener listener) {
        mTopics = topics;
        mListener = listener;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_adapter, parent, false);
        return new MainActivityViewHolder(v, mListener);
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        Topic topic = mTopics.get(position);
        holder.topicName.setText(topic.getName());

        int icon = holder.itemView.getContext().getResources().getIdentifier("pic_" + topic.getPicture(), "drawable", "com.example.cyberseced");
        holder.topicPic.setImageResource(icon);
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public class MainActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView topicName;
        public ImageView topicPic;
        private RecyclerViewClickListener mListener;

        public MainActivityViewHolder(@NonNull View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            topicName = v.findViewById(R.id.topicNameList);
            topicPic = v.findViewById(R.id.topicPicList);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
