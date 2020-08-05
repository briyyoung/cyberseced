package com.example.cyberseced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MoreInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);

        Button Exit = (Button) findViewById(R.id.exit);


        //more info page after quiz
        Exit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MoreInfo.this, LearningEntry.class);
                startActivity(intent);
            }
        });


    }
}
