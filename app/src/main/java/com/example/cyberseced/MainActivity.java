package com.example.cyberseced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE = "This is from MainActivity";
    private static final String TAG = "";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    PostDatabase pDb;
    private Button btnPost;
    private TextInputLayout inputPost;
    private MainActivityAdapter mAdapter;

    public static int postID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.mainActivityView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        btnPost = findViewById(R.id.btnPost);
        inputPost = findViewById(R.id.post_input);

        //create database
        pDb = Room.databaseBuilder(getApplicationContext(), PostDatabase.class, "post-database").build();

        mAdapter = new MainActivityAdapter(this, pDb);
        mRecyclerView.setAdapter(mAdapter);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new StoreUserPost().execute();
//                pDb.getPostDao().insert(new PostEntity(postID, inputPost.getEditText().getText().toString()));
                postID++;
            }
        });
    }

    private class StoreUserPost extends AsyncTask<String, Void, Void > {
        @Override
        protected Void doInBackground(String... ids) {
            String userPost = String.valueOf(inputPost.getEditText().getText());
            pDb.getPostDao().insert(new PostEntity(Integer.parseInt(ids[0]), ids[1]));
            return null;
        }
    }
}
