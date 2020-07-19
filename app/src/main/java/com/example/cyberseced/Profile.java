package com.example.cyberseced;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView profilepic;
    Button changeProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name = findViewById(R.id.ProfileName);
        email = findViewById(R.id.ProfileEmail);
        profilepic = findViewById(R.id.ProfilePic);
        changeProfile = findViewById(R.id.chnageprofile);

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


        Button signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(Profile.this, "You have been logged out", Toast.LENGTH_SHORT).show();


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


        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open gallery
                Intent OpenGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(OpenGallery, 1000);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                profilepic.setImageURI(imageUri);
            }
        }

    }
}
