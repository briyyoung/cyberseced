package com.example.cyberseced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder>{

    private MainActivity mParentActivity;
    PostDatabase pDb;
    private Button btnPost;
    private TextView postTV;

    public MainActivityAdapter(MainActivity mParentActivity, PostDatabase pDb){
        this.mParentActivity = mParentActivity;
        this.pDb = pDb;
    }

    @NonNull
    @Override
    public MainActivityAdapter.MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_adapter, parent, false);
        return new MainActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.MainActivityViewHolder holder, int position) {
        PostEntity entity = pDb.getPostDao().getPostByID(position);
        holder.postTextView.setText(entity.getPostContent());

    }

    public class MainActivityViewHolder extends RecyclerView.ViewHolder {
        public TextView postTextView;

        public MainActivityViewHolder(View v) {
            super(v);
            postTextView = v.findViewById(R.id.tvPost);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
