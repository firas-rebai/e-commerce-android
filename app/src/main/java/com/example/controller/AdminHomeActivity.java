package com.example.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import model.current;

public class AdminHomeActivity extends AppCompatActivity {

    private Button btn_logout, btn_viewCommand , btn_add_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btn_logout = (Button) findViewById(R.id.btn_logout_admin);
        btn_viewCommand = (Button) findViewById(R.id.btn_command);
        btn_add_category = (Button) findViewById(R.id.btn_add_product);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                current.currentUser = null;
                startActivity(intent);
                finish();
            }
        });

//        btn_viewCommand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHomeActivity.this,AdminCommandActivity.class);
//                startActivity(intent);
//            }
//        });

        btn_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AddNewBookActivity.class);
                startActivity(intent);
            }
        });

    }
}
