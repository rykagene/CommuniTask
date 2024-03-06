package com.example.communitask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.firebase.database.ValueEventListener;


public class tab2 extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private DatabaseReference databaseReference;

    public tab2() {
        // Required empty public constructor
    }

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
        cardView1.setOnClickListener(v -> showToast("Clicked on card 1"));
        cardView2.setOnClickListener(v -> showToast("Clicked on card 2"));
        cardView3.setOnClickListener(v -> showToast("Clicked on card 3"));
        cardView4.setOnClickListener(v -> showToast("Clicked on card 4"));
        cardView5.setOnClickListener(v -> showToast("Clicked on card 5"));
        cardView6.setOnClickListener(v -> showToast("Clicked on card 6"));

        // Get references to the tab content LinearLayouts
        LinearLayout hireSomeoneContent = view.findViewById(R.id.tab1_content);
        LinearLayout findSomeoneContent = view.findViewById(R.id.tab2_content);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.round_person_search_24);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.rounded_home_repair_service_24);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    hireSomeoneContent.setVisibility(View.VISIBLE);
                    findSomeoneContent.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    hireSomeoneContent.setVisibility(View.GONE);
                    findSomeoneContent.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        recyclerView = view.findViewById(R.id.recycler_view_posts);
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), postList);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear(); // Clear existing posts before adding new ones

                // Loop through each child snapshot of the dataSnapshot
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract post data from the snapshot
                    String title = snapshot.child("title").getValue(String.class);
                    String content = snapshot.child("content").getValue(String.class);
                    String authorId = snapshot.child("author").getValue(String.class);
                    long timestamp = snapshot.child("timestamp").getValue(Long.class);
                    int rate = snapshot.child("rate").getValue(Integer.class);
                    String location = snapshot.child("location").getValue(String.class);

                    Log.d("PostData", "Title: " + title);
                    Log.d("PostData", "Content: " + content);
                    Log.d("PostData", "AuthorId: " + authorId);
                    Log.d("PostData", "Timestamp: " + timestamp);
                    Log.d("PostData", "Rate: " + rate);
                    Log.d("PostData", "Location: " + location);

                    // Fetch username and icon URL based on authorId from the Users node
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("posts");
//                    Log.d("userRef", "s " + userRef);
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                            if (userSnapshot.exists()) {
                                String username = userSnapshot.child("username").getValue(String.class);
                                // Assuming you have an "icon" field in your Users node
                                String iconUrl = userSnapshot.child("icon").getValue(String.class);

                                // Create a Post object and add it to the postList
                                Post post = new Post(iconUrl, username, title, content, rate, timestamp, location);
                                postList.add(post);
// Log the size of the postList
                                Log.d("PostListSize", "Post lisdasdsat size: "+  postList.size() + "asdasdddd");


                                // Notify the adapter that the dataset has changed
                                postAdapter.notifyDataSetChanged();

                            }
                            else {
                                Log.d("fuck", "Post lisdasdsat size: "+  postList.size() + "asdasdddd");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle database error
                            Log.e("tab2", "Failed to retrieve user data: " + databaseError.getMessage());
                        }
                    });
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(getContext(), "Failed to retrieve posts: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
