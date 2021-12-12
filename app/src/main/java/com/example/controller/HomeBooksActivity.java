package com.example.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import model.book;

public class HomeBooksActivity extends AppCompatActivity implements AdapterBookCard.OnItemClicked {
    RecyclerView recyclerView;
    AdapterBookCard adapter;
    DatabaseReference databaseReference;
    ArrayList<book> bookArrayList;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_books);


        recyclerView = findViewById(R.id.book_recyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        bookArrayList = new ArrayList<>();
        adapter = new AdapterBookCard(this,bookArrayList);

        recyclerView.setAdapter(adapter);
        adapter.setOnClick(HomeBooksActivity.this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
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
    public void onItemClick(int clickedItemIndex) {
        Intent intent = new Intent(HomeBooksActivity.this, BookDetailsActivity.class);
        intent.putExtra("title",bookArrayList.get(clickedItemIndex).getTitle());
        intent.putExtra("price",bookArrayList.get(clickedItemIndex).getPrice());
        intent.putExtra("description",bookArrayList.get(clickedItemIndex).getDescription());
        intent.putExtra("author",bookArrayList.get(clickedItemIndex).getAuthor());
        intent.putExtra("genre",bookArrayList.get(clickedItemIndex).getGenre());
        intent.putExtra("year",bookArrayList.get(clickedItemIndex).getYear());
        startActivity(intent);
    }
}