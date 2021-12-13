package com.example.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.book;
import model.current;

public class UpdateBookActivity extends AppCompatActivity {
    EditText year,title,price,author,genre,description,quantity;
    Button updateButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        year = findViewById(R.id.year_update);
        title = findViewById(R.id.title_update);
        price = findViewById(R.id.price_update);
        author = findViewById(R.id.author_update);
        description = findViewById(R.id.description_update);
        genre = findViewById(R.id.genre_update);
        quantity = findViewById(R.id.quantity_update);
        updateButton = findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                book book = new book(title.getText().toString(),author.getText().toString(),genre.getText().toString(),year.getText().toString(),Float.parseFloat(price.getText().toString()),description.getText().toString(),Integer.parseInt(quantity.getText().toString()));
                mDatabase.child("books").child(book.getTitle()).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UpdateBookActivity.this,"book updated",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            title.setText(extras.getString("title"));
            description.setText(extras.getString("description"));
            year.setText(extras.getString("year"));
            price.setText(extras.getString("price"));
            author.setText(extras.getString("author"));
            genre.setText(extras.getString("genre"));
        }
    }
}