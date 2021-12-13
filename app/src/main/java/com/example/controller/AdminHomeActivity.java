package com.example.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import model.current;

public class AdminHomeActivity extends AppCompatActivity {

    private Button btn_logout, btn_viewbooks , btn_add_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btn_logout = (Button) findViewById(R.id.btn_logout_admin);
        btn_viewbooks = (Button) findViewById(R.id.btn_books);
        btn_add_book = (Button) findViewById(R.id.btn_add_product);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, FirstActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                current.currentUser = null;
                current.isAdmin = false;
                startActivity(intent);
                finish();
            }
        });

        btn_viewbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this,HomeBooksActivity.class);
                startActivity(intent);
            }
        });


        btn_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AddNewBookActivity.class);
                startActivity(intent);
            }
        });

    }
}
