package com.example.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import model.book;
import model.current;

public class BookDetailsActivity extends AppCompatActivity {
    TextView title, price, description, year, genre, author;
    Button deleteButton,updateButton,addToCart;
    model.book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        title = findViewById(R.id.title_details);
        year = findViewById(R.id.year_details);
        price = findViewById(R.id.price_details);
        description = findViewById(R.id.description_details);
        genre = findViewById(R.id.genre_details);
        author = findViewById(R.id.author_details);
        deleteButton = findViewById(R.id.delete_button_details);
        updateButton = findViewById(R.id.update_button_details);
        addToCart = findViewById(R.id.add_cart_details);


        Bundle extras = getIntent().getExtras();

        if(!current.isAdmin){
            deleteButton.setEnabled(false);
            updateButton.setEnabled(false);
        }


        if (extras != null) {
            book = (model.book) extras.getSerializable("book");
            title.setText(book.getTitle());
            description.setText(book.getDescription());
            year.setText(book.getYear());
            price.setText(String.valueOf(book.getPrice()));
            author.setText(book.getAuthor());
            genre.setText(book.getAuthor());

        }
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("orders").child(book.getTitle()).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(BookDetailsActivity.this,"book added to your cart",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("books").orderByChild("title").equalTo(book.getTitle());

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailsActivity.this, UpdateBookActivity.class);
                intent.putExtra("title",title.getText());
                intent.putExtra("price",price.getText());
                intent.putExtra("description",description.getText());
                intent.putExtra("author",author.getText());
                intent.putExtra("genre",genre.getText());
                intent.putExtra("year",year.getText());
                startActivity(intent);
            }
        });


    }
}