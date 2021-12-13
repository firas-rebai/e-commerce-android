package com.example.controller;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import model.book;

public class AddNewBookActivity extends AppCompatActivity {
    EditText year,author,title,description,quantity,price,genre;
    Button add_book_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_product);

        year = findViewById(R.id.book_year);
        author = findViewById(R.id.book_author);
        title = findViewById(R.id.book_name);
        description = findViewById(R.id.book_description);
        quantity = findViewById(R.id.book_quantity);
        price = findViewById(R.id.book_price);
        genre = findViewById(R.id.book_genre);

        add_book_btn = findViewById(R.id.add_new_book);
        add_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_book();
            }
        });

    }

    private void add_book() {
        final DatabaseReference DBref;
        DBref = FirebaseDatabase.getInstance().getReference();
        DBref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                book book = new book(title.getText().toString(),author.getText().toString(),genre.getText().toString(),year.getText().toString(),Float.parseFloat(price.getText().toString()),description.getText().toString(),Integer.parseInt(quantity.getText().toString()));
                DBref.child("books").child(book.getTitle()).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddNewBookActivity.this,"book added",Toast.LENGTH_LONG).show();
                            year.setText("");
                            title.setText("");
                            description.setText("");
                            price.setText("");
                            quantity.setText("");
                            author.setText("");
                            genre.setText("");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
