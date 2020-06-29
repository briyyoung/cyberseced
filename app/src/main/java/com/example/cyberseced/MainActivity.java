package com.example.cyberseced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    public static final String CHOICE = "ggg" ;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startRecyclerView();
    }





    private void startRecyclerView() {
        mRecyclerView = findViewById(R.id.mainActivityView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        MainActivityAdapter.RecyclerViewClickListener listener = new MainActivityAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Modules modules = Modules.getModules().get(position);
                Intent intent = new Intent(MainActivity.this, Learning.class);
                intent.putExtra(CHOICE, modules.getName());

                startActivity(intent);

            }
        };







    mAdapter = new MainActivityAdapter(Modules.getModules(), listener);
        mRecyclerView.setAdapter(mAdapter);

    }
}



