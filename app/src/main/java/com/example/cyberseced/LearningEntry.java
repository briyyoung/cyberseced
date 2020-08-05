package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
/***************************************************************************************
 *    REFERENCES
 *    Title: Android Beginner Tutorial #16 - Play YouTube Videos using Android Player API in Android Studio
 *    Author: CodingWithMitch
 *    Date: 24/3/2017
 *    Code version: 24/3/2017
 *    Availability: https://www.youtube.com/watch?v=W4hTJybfU7s
 *
 *    Title: How to add a Bottom Navigation Bar in Android
 *    Author: Suragch
 *    Date: 13/1/2019
 *    Availability: https://medium.com/@suragch/how-to-add-a-bottom-navigation-bar-in-android-958ed728ef6c
 *
 ***************************************************************************************/
public class LearningEntry extends AppCompatActivity {
    public static final String CHOICE = " ";
    public static final String CAT = " ";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseAuth firebaseAuth;
    private BottomNavigationView bottomNavigation;
    private Button contactUsBtn;
    private FloatingActionButton buttonFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_entry);

        startRecyclerView();

        //contact via mail
        contactUsBtn = findViewById(R.id.contactBtn);
        contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {"testlah8@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry to UNSW");
                intent.putExtra(Intent.EXTRA_TEXT, " ");
                intent.putExtra(Intent.EXTRA_CC, "testlah8@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send Feedback "));
            }
        });

        buttonFAB = findViewById(R.id.fabPost);
        buttonFAB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(LearningEntry.this, Post.class);
                startActivity(intent3);
            }
        });

        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setSelectedItemId(R.id.navigation_quiz);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home:
                        Intent intent2 = new Intent(LearningEntry.this, Profile.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation_forum:
                        Intent intent1 = new Intent(LearningEntry.this, PostFeed.class);
                        startActivity(intent1);
                        return true;
                }
                return false;
            }
        });


    }


    private void startRecyclerView() {
        mRecyclerView = findViewById(R.id.mainActivityView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        LearningAdapter.RecyclerViewClickListener listener = new LearningAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Modules modules = Modules.getModules().get(position);
                Intent intent = new Intent(LearningEntry.this, LearningHome.class);
                intent.putExtra(CHOICE, modules.getName());
                startActivity(intent);
            }
        };

        mAdapter = new LearningAdapter(Modules.getModules(), listener);
        mRecyclerView.setAdapter(mAdapter);
    }


}