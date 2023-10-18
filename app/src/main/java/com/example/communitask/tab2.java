package com.example.communitask;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;


// FIND TAB
// FIND TAB
// FIND TAB

public class tab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        // Get references to the MaterialCardViews
        MaterialCardView cardView1 = view.findViewById(R.id.cardView1);
        MaterialCardView cardView2 = view.findViewById(R.id.cardView2);
        MaterialCardView cardView3 = view.findViewById(R.id.cardView3);
        MaterialCardView cardView4 = view.findViewById(R.id.cardView4);
        MaterialCardView cardView5 = view.findViewById(R.id.cardView5);
        MaterialCardView cardView6 = view.findViewById(R.id.cardView6);

        // Set click listeners for the MaterialCardViews
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for cardView1
                showToast("Clicked on card 1");
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for cardView2
                showToast("Clicked on card 2");
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for cardView3
                showToast("Clicked on card 3");
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for cardView4
                showToast("Clicked on card 4");
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for cardView5
                showToast("Clicked on card 5");
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for cardView6
                showToast("Clicked on card 6");
            }
        });





        // Get references to the tab content LinearLayouts
        LinearLayout hireSomeoneContent = view.findViewById(R.id.tab1_content);
        LinearLayout findSomeoneContent = view.findViewById(R.id.tab2_content);



        // Get the TabLayout
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        // Set a listener to show the content for the selected tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Check which tab is selected and show the respective content
                if (tab.getPosition() == 0) { // Hire Someone tab
                    hireSomeoneContent.setVisibility(View.VISIBLE);
                    findSomeoneContent.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) { // Find Someone tab
                    hireSomeoneContent.setVisibility(View.GONE);
                    findSomeoneContent.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do something when a tab is unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do something when a tab is reselected
            }
        });

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
