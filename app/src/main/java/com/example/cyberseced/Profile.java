package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    TextView name, email;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name = findViewById(R.id.ProfileName);
        email = findViewById(R.id.ProfileEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();
            DocumentReference documentReference = firestore.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    name.setText(documentSnapshot.getString("tname"));
                    email.setText(documentSnapshot.getString("temail"));
                }
            });


        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    //       case R.id.navigation_home:
                    //         Intent intent2 = new Intent(Profile.this, Profile.class);
                    //      startActivity(intent2);
                    //           break;
                    case R.id.navigation_quiz:
                        Intent intent3 = new Intent(Profile.this, LearningEntry.class);
                        startActivity(intent3);
                        return true;
                    case R.id.navigation_forum:
                        Intent intent1 = new Intent(Profile.this, ForumHome.class);
                        startActivity(intent1);
                        return true;
                }
                return false;
            }
        });
    }


}
