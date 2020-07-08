package com.example.cyberseced;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText eemail, epassword;
    Button blogin;
    FirebaseAuth firebaseAuth;
    TextView tforgot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        eemail = findViewById(R.id.loginname);
        epassword = findViewById(R.id.loginpassword);
        firebaseAuth = FirebaseAuth.getInstance();
        blogin = findViewById(R.id.loginbutton);
        tforgot = findViewById(R.id.forgotpassword);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),LearningEntry.class));
            finish();
            Toast.makeText(Login.this, "Your are already logged in", Toast.LENGTH_SHORT).show();
        }

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eemail.getText().toString().trim();
                String password = epassword.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LearningEntry.class));
                        } else {
                            Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        tforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText reset = new EditText(v.getContext());
                AlertDialog.Builder resetDialog = new AlertDialog.Builder(v.getContext());
                resetDialog.setTitle("Reset password?");
                resetDialog.setMessage("Enter your email");
                resetDialog.setView(reset);

                resetDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String password = reset.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(password).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Link has been sent to your email", Toast.LENGTH_SHORT).show();
                            }

                        });

                     firebaseAuth.sendPasswordResetEmail(password).addOnFailureListener(new OnFailureListener() {
                           @Override
                            public void onFailure(@NonNull Exception e) {
                               Toast.makeText(Login.this, "Link not sent", Toast.LENGTH_SHORT).show();
                     }
                      });


                    }
                });


                resetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                resetDialog.create().show();

            }


        });


    }


}
