package com.example.cyberseced;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/***************************************************************************************
 *    Title: Edit Full Profile | Firebase User | Firebase Email Authentication Tutorial
 *    Author: SmallAcademy
 *    Date: 24/4/2020
 *    Code version: 24/4/2020
 *    Availability: https://github.com/bikashthapa01?tab=repositories
 *    Availability: https://www.youtube.com/playlist?list=PLlGT4GXi8_8dDK5Y3KCxuKAPpil9V49rN
 *
 ***************************************************************************************/

public class EditProfile extends AppCompatActivity {
    EditText editname, editemail;
    Button save;
    ImageView editpic;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    FirebaseUser firebaseUser;
    StorageReference storageReference;

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        //get data from profile
        Intent data = getIntent();
        String name = data.getStringExtra("name");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");

        //instances of objects
        editemail = findViewById(R.id.EditEmail);
        editname = findViewById(R.id.EditName);
        editpic = findViewById(R.id.editPic);
        save = findViewById(R.id.EditSave);

        //create instances of firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        editemail.setText(email);
        editname.setText(name);

        //get profile pic for current user
        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(editpic);
            }
        });

        //open gallery to change picture
        editpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent OpenGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(OpenGallery, 1000);
            }
        });

        //save new information to firebase
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editname.getText().toString().isEmpty() || editemail.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfile.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = editemail.getText().toString();
                firebaseUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference documentReference = firestore.collection("users").document(firebaseUser.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("temail", email);
                        edited.put("tname", editname.getText().toString());
                        documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Profile.class));
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditProfile.this, "Update failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Toast.makeText(EditProfile.this, "Updates saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        Log.d(TAG, "onCreate: " + name + email + phone);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                upload(imageUri);
            }
        }

    }

    //upload new profile picture
    private void upload(Uri imageUri) {

        final StorageReference fileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(EditProfile.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(editpic);
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
