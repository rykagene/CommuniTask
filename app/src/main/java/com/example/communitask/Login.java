package com.example.communitask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private MaterialButton btn_login;
    private TextInputEditText et_email,et_password;
    private TextView tv_signup;
    private FirebaseAuth mAuth;
    //    private DatabaseReference usersRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        listener();

    }
    //binding views
    private void initialize() {
        tv_signup = findViewById(R.id.tv_signup);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
//        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    private void listener() {
        btn_login.setOnClickListener(v->{
            String email = String.valueOf(et_email.getText());
            String password = String.valueOf(et_password.getText());
            loginAccount(email,password);
        });

        //open registration activity
        tv_signup.setOnClickListener(v->{
            Intent registration = new Intent(this,Registration.class);
            startActivity(registration);
        });
    }

    private void loginAccount(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return; // Return early if fields are empty
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, proceed to main page
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish(); //remove the process of login page
                    } else {
                        Toast.makeText(Login.this, "Login failed. Make sure you check your credentials",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }


//    private void retrieveUserData() {
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser != null) {
//            String currentUserId = currentUser.getUid();
//            usersRef.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    String username = dataSnapshot.child("username").getValue(String.class);
//                    String email = dataSnapshot.child("email").getValue(String.class);
//
//                    // Cache the retrieved data
//                    UserDataManager.getInstance().setUserData(username, email);
//
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    // Handle database error
//                    Log.e("FirebaseError", "Error retrieving data: " + databaseError.getMessage());
//                }
//            });
//        } else {
//            Toast.makeText(this, "Authentication Problem", Toast.LENGTH_SHORT).show();
//        }
//    }


}