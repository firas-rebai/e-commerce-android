package com.example.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import model.current;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView username,email,isadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = findViewById(R.id.username_profile);
        username.setText(current.currentUser.getUsername());
        email = findViewById(R.id.email_profile);
        email.setText(current.currentUser.getEmail());
        isadmin = findViewById(R.id.isadmin_profile);
        if(current.isAdmin) isadmin.setText("Administrator");
        else isadmin.setText("");
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cart:
                        startActivity(new Intent(ProfileActivity.this, CartActitvity.class));
                        return true;
                    case R.id.home:
                        startActivity(new Intent(ProfileActivity.this, HomeBooksActivity.class));
                        return true;

                }
                return false;
            }
        });
    }
}