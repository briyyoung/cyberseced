package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity  {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


       bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {

            public boolean onNavigationItemSelected (@NonNull MenuItem item){

            int id = item.getItemId();
            switch (id) {
                //       case R.id.navigation_home:
                //         Intent intent2 = new Intent(Profile.this, Profile.class);
                //      startActivity(intent2);
                //           break;
                case R.id.navigation_quiz:
                    Intent intent3 = new Intent(Profile.this, LearningEntry.class);
                    startActivity(intent3);
                    break;
                case R.id.navigation_forum:
                    Intent intent1 = new Intent(Profile.this, ForumHome.class);
                    startActivity(intent1);
                    break;
            }
            return false;
        }
        });
    }








}
