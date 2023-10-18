package com.example.communitask;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.communitask.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new tab1());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home ){
                replaceFragment(new tab1());
                return true;
            } else if (item.getItemId() == R.id.homeFindJob) {
                replaceFragment(new tab2());
                return true;
            } else if (item.getItemId() == R.id.homeMessage) {
                replaceFragment(new tab3());
                return true;
            } else if (item.getItemId() == R.id.homeNotif) {
                replaceFragment(new tab4());
                return true;
            } else if (item.getItemId() == R.id.homeProfile) {
                replaceFragment(new tab5());
                return true;
            } else {
                return false; // Handle other cases if needed
            }
        });




    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}