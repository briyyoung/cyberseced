package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private static final String TAG = "";
    TextView tname, temail, tpassword;
        Button bregister;
        FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        tname = findViewById(R.id.name);
        temail = findViewById(R.id.email);
        tpassword = findViewById(R.id.password);
        bregister = findViewById(R.id.rego);

        firebaseAuth =  FirebaseAuth.getInstance();

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = temail.getText().toString().trim();
                String password = tpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    temail.setError("Email is needed!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    tpassword.setError("Password is needed!");
                    return;
                }

                        //successful creation
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else {
                            Toast.makeText(Signup.this, "Error encountered", Toast.LENGTH_SHORT).show();

                                                    }

                    }
                });

            }
        });{}

    }
}


