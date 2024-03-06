package com.example.communitask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.communitask.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private DatabaseReference usersRef;

    private void initialize() {
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        retrieveUserData();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new tab2());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
             if (item.getItemId() == R.id.home) {
                 replaceFragment(new tab2());
                 return true;
             }
             else if (item.getItemId() == R.id.chats) {
                replaceFragment(new ChatFragment());
                return true;
            }
             else if (item.getItemId() == R.id.create) {
                 Intent intent = new Intent(MainActivity.this, CreatePost.class);
                 startActivity(intent);
                 return true;
             }
             else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
                return true;
            }
             else if (item.getItemId() == R.id.notif) {
                 replaceFragment(new NotifsFragment());
                 return true;
             }else {
                return false; // Handle other cases if needed
            }
        });

        // Check if user is signed in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            // If not signed in, go back to login page
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Finish the current activity
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Check if user is signed in
        if (user == null) {
            // If not signed in, go back to login page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); // Go back to login page
            finish(); // Finish the current activity
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void retrieveUserData() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();
            usersRef.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String username = dataSnapshot.child("username").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String icon = dataSnapshot.child("icon").getValue(String.class);

                    // Cache the retrieved data
                    UserDataManager.getInstance().setUserData(username, email,icon);
                    UserDataManager.getInstance().setUserId(currentUserId); // Set the user_id

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                    Log.e("FirebaseError", "Error retrieving data: " + databaseError.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "Authentication Problem", Toast.LENGTH_SHORT).show();
        }
    }

}