package com.example.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register=(TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) { switch (v.getId()){
        case R.id.register:
            startActivity(new Intent(getApplicationContext(),register.class));
            break;
    }
    }

}