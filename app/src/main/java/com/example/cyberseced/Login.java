package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText eemail, epassword;
    Button blogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        eemail = findViewById(R.id.loginname);
        epassword = findViewById(R.id.loginpassword);;
        firebaseAuth = FirebaseAuth.getInstance();
        blogin = findViewById(R.id.loginbutton);;

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eemail.getText().toString().trim();
                String password = epassword.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LearningEntry.class));
                        }

                        else{
                            Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }




}
