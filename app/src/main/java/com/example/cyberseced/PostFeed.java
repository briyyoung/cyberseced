package com.example.cyberseced;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class PostFeed extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseRecyclerAdapter adapter;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        //initialize recyclerview and FIrebase objects
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser()==null){
                    Intent loginIntent = new Intent(PostFeed.this, Signup.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);startActivity(loginIntent);
                }
            }
        };


    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseRecyclerOptions<Feed> options = new FirebaseRecyclerOptions.Builder<Feed>()
                        .setQuery(mDatabase, Feed.class )
                        .build();

         adapter = new FirebaseRecyclerAdapter<Feed, BlogzoneViewHolder>(options) {
            @NonNull
            @Override
            public BlogzoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_test, parent, false);

                return new BlogzoneViewHolder(v);

            }

            @Override
            protected void onBindViewHolder(@NonNull BlogzoneViewHolder blogzoneViewHolder, int i, @NonNull Feed blogzone) {
                final String post_key = getRef(i).getKey().toString();
                blogzoneViewHolder.setTitle((String) blogzone.getTitle());
                blogzoneViewHolder.setDesc(blogzone.getDesc());
                blogzoneViewHolder.setImageUrl(getApplicationContext(), blogzone.getImageUrl());
                blogzoneViewHolder.setUserName(blogzone.getUsername());
                blogzoneViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent singleActivity = new Intent(PostFeed.this, SinglePost.class);
                        singleActivity.putExtra("PostID", post_key);
                        startActivity(singleActivity);

                    }

                });

            }


        };



        adapter.startListening();

        recyclerView.setAdapter(adapter);
    }

    public class BlogzoneViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogzoneViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = mView.findViewById(R.id.post_title_txtview);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
            post_desc.setText(desc);
        }
        public void setImageUrl(Context ctx, String imageUrl){
            ImageView post_image = mView.findViewById(R.id.post_image);
            Picasso.get().load(imageUrl).into(post_image);
        }
        public void setUserName(String userName){
            TextView postUserName = mView.findViewById(R.id.post_user);
            postUserName.setText(userName);
        }
    }





    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

