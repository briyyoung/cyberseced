package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {


    TextView tname, temail, tpassword;
    Button bregister;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        tname = findViewById(R.id.signupname);
        temail = findViewById(R.id.signupemail);
        tpassword = findViewById(R.id.signuppassword);
        bregister = findViewById(R.id.signupbutton);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), LearningEntry.class));
            finish();
            Toast.makeText(Signup.this, "Your are logged in", Toast.LENGTH_SHORT).show();
        }

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = temail.getText().toString().trim();
                String password = tpassword.getText().toString().trim();
                final String name = tname.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    tname.setError("I've never met someone called *blank* before. Please enter a name");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    temail.setError("Email is needed!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    tpassword.setError("Wow! You really want to be hacked, don't you? Please enter a password!");
                    return;
                }

                if (tpassword.length() < 8) {
                    tpassword.setError("Password must be 8 characters or more");
                }

                //successful creation
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup.this, "User created", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = firestore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("tname", name);
                            user.put("temail", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "Success" + userID);
                                }
                            })

                          .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "Failure" + e.toString());
                                }

                            });

                            startActivity(new Intent(getApplicationContext(), Home.class));

                        } else {
                            Log.e("TAG", "onComplete: Failed=" + task.getException().getMessage());
                            Toast.makeText(Signup.this, "Error encountered", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });
        {
        }

    }
}


