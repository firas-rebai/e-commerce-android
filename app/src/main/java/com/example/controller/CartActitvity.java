package com.example.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import model.book;
import model.current;

public class CartActitvity extends AppCompatActivity implements AdapterBookCart.OnItemClicked {

    private RecyclerView recyclerView;
    private AdapterBookCart adapter;
    private ArrayList<book> bookArrayList;
    DatabaseReference databaseReference;
    BottomNavigationView bottomNavigationView;
    TextView totalPrice;
    float total;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        bookArrayList.clear();
        adapter.notifyDataSetChanged();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child(current.currentUser.getUsername()).child("orders").getChildren()) {
                    book book = dataSnapshot.getValue(book.class);
                    bookArrayList.add(book);
                    total += book.getPrice();
                }
                totalPrice.setText(String.valueOf(total));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_actitvity);

        recyclerView = findViewById(R.id.cart_recyclerview);
        totalPrice = findViewById(R.id.price_total_textview);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookArrayList = new ArrayList<>();
        adapter = new AdapterBookCart(this, bookArrayList);

        recyclerView.setAdapter(adapter);
        adapter.setOnClick(CartActitvity.this);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(CartActitvity.this, HomeBooksActivity.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(CartActitvity.this, ProfileActivity.class));
                        break;

                }
                return false;
            }
        });






        ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.RIGHT:
                        final book book = bookArrayList.get(position);
                        bookArrayList.add(book);
                        bookArrayList.remove(position);
                        adapter.notifyItemRemoved(position);

                        Snackbar.make(recyclerView, "Book removed from cart.", Snackbar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        bookArrayList.remove(bookArrayList.lastIndexOf(book));
                                        bookArrayList.add(position, book);
                                        adapter.notifyItemInserted(position);
                                    }
                                }).show();

                        break;
                }

            }
        };
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);

    }


    @Override
    public void onItemClick(int clickedItemIndex) {
        Intent intent = new Intent(CartActitvity.this, BookDetailsActivity.class);
        intent.putExtra("title", bookArrayList.get(clickedItemIndex).getTitle());
        intent.putExtra("price", bookArrayList.get(clickedItemIndex).getPrice());
        intent.putExtra("description", bookArrayList.get(clickedItemIndex).getDescription());
        intent.putExtra("author", bookArrayList.get(clickedItemIndex).getAuthor());
        intent.putExtra("genre", bookArrayList.get(clickedItemIndex).getGenre());
        intent.putExtra("year", bookArrayList.get(clickedItemIndex).getYear());
        startActivity(intent);
    }
}