package com.example.communitask;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.locationcomponent.LocationComponentPlugin;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreatePost extends AppCompatActivity {

    private ImageButton backBtn;

    private MaterialButton createPost;
    private DatabaseReference databaseReference;

    private String userId;
    private MapView mapView;
    private FloatingActionButton floatingActionButton;
    private LocationComponentPlugin locationComponentPlugin;
    private OnIndicatorBearingChangedListener onIndicatorBearingChangedListener;
    private OnIndicatorPositionChangedListener onIndicatorPositionChangedListener;
    private com.mapbox.maps.plugin.gestures.OnMoveListener onMoveListener;
    private ActivityResultLauncher<String> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        initialize();
        setupListeners();

        // Retrieve user_id from UserDataManager
        userId = UserDataManager.getInstance().getUserId();
        if (userId == null) {
            // Handle error if user_id is null
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity
        }

        // Use userId when creating a new post
        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");
    }

    private void setupListeners() {
        backBtn.setOnClickListener(v -> finish());

        createPost.setOnClickListener(v -> {
            // Get post title and content from input fields
            String title = getInputText(R.id.text_title);
            String content = getInputText(R.id.text_content);
            String location = getInputText(R.id.text_location);

            TextInputEditText rateEditText = findViewById(R.id.text_rate);
            String rateString = rateEditText.getText().toString();
            int rate;
            try {
                rate = Integer.parseInt(rateString);
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                rate = 0; // Or any default value you prefer
            }

            // Check if title or content is empty
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a unique ID for the post
            String postId = databaseReference.push().getKey();

            // Get current timestamp
            long timestamp = System.currentTimeMillis();

            // Create a HashMap to represent the post data
            Map<String, Object> postData = new HashMap<>();
            postData.put("title", title);
            postData.put("content", content);
            postData.put("author", userId); // Replace "<user_id>" with the actual user ID
            postData.put("timestamp", timestamp); // Include timestamp
            postData.put("location", location);
            postData.put("rate", rate);

            // Push the post data to the database
            databaseReference.child(postId).setValue(postData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(CreatePost.this, "Post created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(CreatePost.this, "Failed to create post: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private void initialize() {
        backBtn = findViewById(R.id.backBtn);
        createPost = findViewById(R.id.create);
    }

    // Helper method to get input text from TextInputEditText
    private String getInputText(int id) {
        return Objects.requireNonNull(((TextInputEditText) findViewById(id)).getText()).toString().trim();
    }


}