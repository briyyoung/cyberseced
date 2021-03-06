package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/***************************************************************************************
 *    REFERNCES
 *
 *    Title: Blogzone
 *    Author: kenny-io
 *    Date: 20/7/20
 *    Code version: 29 Nov 2017
 *    Availability: https://medium.com/@peterekeneeze/build-a-simple-blog-app-with-firebase-in-android-studio-b6482275408
 *    Availability: https://github.com/kenny-io/Blogzone
 *
 ***************************************************************************************/

public class PostFeed extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseRecyclerAdapter adapter;
    private BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);

        //initialize recyclerview and FIrebase objects
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(PostFeed.this, Signup.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                }
            }
        };

        //navigate to other activities
        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setSelectedItemId(R.id.navigation_forum);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home:
                        Intent intent2 = new Intent(PostFeed.this, Profile.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation_quiz:
                        Intent intent1 = new Intent(PostFeed.this, LearningEntry.class);
                        startActivity(intent1);
                        return true;
                }
                return false;
            }
        });

    }

    //detect new changes
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseRecyclerOptions<Feed> options = new FirebaseRecyclerOptions.Builder<Feed>()
                .setQuery(mDatabase, Feed.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Feed, PostFeedViewHolder>(options) {
            @NonNull
            @Override
            public PostFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_items, parent, false);

                return new PostFeedViewHolder(v);

            }

            @Override
            protected void onBindViewHolder(@NonNull PostFeedViewHolder postFeedViewHolder, int i, @NonNull Feed feed) {
                final String post_key = getRef(i).getKey();
                postFeedViewHolder.setTitle(feed.getTitle());
                postFeedViewHolder.setDesc(feed.getDesc());

            }


        };


        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    //set post text to layouts
    public class PostFeedViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public PostFeedViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = mView.findViewById(R.id.post_title_txtview);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
            post_desc.setText(desc);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

