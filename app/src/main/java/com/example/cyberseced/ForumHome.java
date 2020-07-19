package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ForumHome extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_home);


        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setSelectedItemId(R.id.navigation_forum);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {

            public boolean onNavigationItemSelected (@NonNull MenuItem item){

                int id = item.getItemId();
                switch (id) {
                          case R.id.navigation_home:
                            Intent intent2 = new Intent(ForumHome.this, Profile.class);
                          startActivity(intent2);
                              return true;
                    case R.id.navigation_quiz:
                        Intent intent3 = new Intent(ForumHome.this, LearningEntry.class);
                        startActivity(intent3);
                        return true;
                 //   case R.id.navigation_forum:
               //         Intent intent1 = new Intent(ForumHome.this, ForumHome.class);
               //         startActivity(intent1);
                //        return true;
                }
                return false;
            }
        });
    }


    }

