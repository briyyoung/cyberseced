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
import com.google.firebase.auth.FirebaseAuth;

public class LearningEntry extends AppCompatActivity {
    public static final String CHOICE = " ";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigation;
    private Button contactUsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_entry);

        startRecyclerView();


        Button signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(LearningEntry.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(LearningEntry.this, "You have been logged out", Toast.LENGTH_SHORT).show();


            }
        });
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
                    //   case R.id.navigation_quiz:
                    //        Intent intent3 = new Intent(LearningEntry.this, LearningEntry.class);
                    //        startActivity(intent3);
                    //         break;
                    case R.id.navigation_forum:
                        Intent intent1 = new Intent(LearningEntry.this, ForumHome.class);
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