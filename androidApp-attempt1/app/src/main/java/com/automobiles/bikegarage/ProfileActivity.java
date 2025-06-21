package com.automobiles.bikegarage;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView displayNameTextView, emailTextView, shopNameTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        displayNameTextView = findViewById(R.id.display_name);
        emailTextView = findViewById(R.id.email);
        shopNameTextView = findViewById(R.id.shop_name);

        if (user != null) {
            displayNameTextView.setText(user.getDisplayName());
            emailTextView.setText(user.getEmail());

            // Load shop name from Firebase
            userReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
            userReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String shopName = dataSnapshot.child("shopName").getValue(String.class);
                    shopNameTextView.setText(shopName);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
        }
    }
}
