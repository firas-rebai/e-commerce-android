package com.example.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import model.book;

public class HomeBooksActivity extends AppCompatActivity implements AdapterBookCard.OnItemClicked {
    RecyclerView recyclerView;
    AdapterBookCard adapter;
    DatabaseReference databaseReference;
    ArrayList<book> bookArrayList;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        bookArrayList.clear();
        adapter.notifyDataSetChanged();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    book book = dataSnapshot.getValue(book.class);
                    bookArrayList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_books);


        recyclerView = findViewById(R.id.book_recyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bookArrayList = new ArrayList<>();
        adapter = new AdapterBookCard(this, bookArrayList);

        recyclerView.setAdapter(adapter);
        adapter.setOnClick(HomeBooksActivity.this);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cart:
                        startActivity(new Intent(HomeBooksActivity.this, CartActitvity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(HomeBooksActivity.this, ProfileActivity.class));
                        return true;

                }
                return false;
            }
        });

    }


    @Override
    public void onItemClick(int clickedItemIndex) {
        Intent intent = new Intent(HomeBooksActivity.this, BookDetailsActivity.class);
        intent.putExtra("book", bookArrayList.get(clickedItemIndex));
        startActivity(intent);
    }
}