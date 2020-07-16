package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

public class LearningEntry extends AppCompatActivity {
    public static final String CHOICE = " ";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_entry);

        startRecyclerView();

        Button signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(LearningEntry.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(LearningEntry.this, "You have been logged out", Toast.LENGTH_SHORT).show();


            }
        });

    }



    private void startRecyclerView() {
        mRecyclerView = findViewById(R.id.mainActivityView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        LearningAdapter.RecyclerViewClickListener listener = new LearningAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Modules modules = Modules.getModules().get(position);
                Intent intent = new Intent(LearningEntry.this, LearningHome.class);
                intent.putExtra(CHOICE, modules.getName());

                  startActivity(intent);

            }
        };

        mAdapter = new LearningAdapter(Modules.getModules(), listener);
        mRecyclerView.setAdapter(mAdapter);


    }
}