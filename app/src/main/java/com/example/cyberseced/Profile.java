package com.example.cyberseced;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

// https://www.youtube.com/watch?v=qANyvTysn04

public class Profile extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    TextView name, email;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    String userId;
    ImageView profilepic;
    Button changeProfile, resetPassword;
    StorageReference storageReference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name = findViewById(R.id.ProfileName);
        email = findViewById(R.id.ProfileEmail);
        profilepic = findViewById(R.id.ProfilePic);
        changeProfile = findViewById(R.id.changeprofile);
        resetPassword = findViewById(R.id.changepassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilepic);
            }
        });

        userId = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("tname"));
                email.setText(documentSnapshot.getString("temail"));
            }
        });


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText changepassword = new EditText(v.getContext());
                AlertDialog.Builder resetDialog = new AlertDialog.Builder(v.getContext());
                resetDialog.setTitle("Reset password?");
                resetDialog.setMessage("Enter new password");
                resetDialog.setView(changepassword);

                resetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String newpassword = changepassword.getText().toString();

                        firebaseUser.updatePassword(newpassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Profile.this, "Password Changed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Profile.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    });

                        resetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        final AlertDialog alertDialog = resetDialog.create();
                        alertDialog.show();


                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean wantToCloseDialog = (changepassword.getText().toString().trim().isEmpty());
                                // if EditText is empty disable closing on positive button
                                if (!wantToCloseDialog)
                                    alertDialog.dismiss();
                            }
                        });


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

                        Intent i = new Intent(v.getContext(),EditProfile.class);
                        i.putExtra("name" , name.getText().toString());
                        i.putExtra("email" ,email.getText().toString());


                        startActivity(i);




                    }
                });


            }






}
