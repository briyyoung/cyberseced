package com.example.cyberseced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MainActivityAdapter.OnMainAdapterListener {
    public static final String CHOICE = "ggg" ;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertModules();
        startRecyclerView();
    }

    private ArrayList<Modules> mList = new ArrayList<>();


    private void insertModules() {
        mList.add(new Modules(R.drawable.cyber, "Social Engineering Attacks"));
        mList.add(new Modules(R.drawable.cyber, "Psychology Based Attacks"));
        mList.add(new Modules(R.drawable.cyber, "How to stay safe online"));
        mList.add(new Modules(R.drawable.cyber, "Work from Home: How to secure your remote/home workplace"));
        mList.add(new Modules(R.drawable.cyber, "Best practices - Passwords"));
        mList.add(new Modules(R.drawable.cyber, "Physical Security – Shoulder Surfing, Tailgating, Dumpster Diving"));

    }

    private void startRecyclerView() {
        mRecyclerView = findViewById(R.id.mainActivityView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainActivityAdapter(mList, this );
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        MainActivityAdapter.OnMainAdapterListener listener = new MainActivityAdapter.OnMainAdapterListener() {
            @Override
            public void OnMainAdapterClick(View view, int position) {
                Modules modules = Modules.getModules().get(position);
               Intent intent = new Intent(MainActivity.this, Learning.class);
                intent.putExtra(CHOICE, modules.getName());
            }


        };



    }


    @Override
    public void OnMainAdapterClick(View view, int position) {
        Modules modules = Modules.getModules().get(position);
        Intent intent = new Intent(MainActivity.this, Learning.class);
        intent.putExtra(CHOICE, modules.getName());

     //   System.out.println(modules.getName());
        startActivity(intent);

    }
}



