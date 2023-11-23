package com.example.communitask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pre_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_home);

        Button jobseeker = findViewById(R.id.jobseeker); // Replace R.id.button with your button's ID
        jobseeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pre_home.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button employer = findViewById(R.id.employer); // Replace R.id.button with your button's ID
        employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pre_home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}