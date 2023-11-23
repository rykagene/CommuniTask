package com.example.communitask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button backBTN = findViewById(R.id.backBTN); // Replace R.id.button with your button's ID
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });

        CheckBox agreeCheckBox = findViewById(R.id.agreeCheckBox);
        Button confirmBtn = findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agreeCheckBox.isChecked()) {
                    Intent intent = new Intent(Registration.this, pre_home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Registration.this, "To proceed, kindly agree to our terms and conditions by checking the box.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}