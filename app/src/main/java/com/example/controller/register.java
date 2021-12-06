package com.example.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import model.user;

public class register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText fn;
    private EditText pass1;
    private EditText pass2;
    private EditText email;
    private TextView erja3login;
    private Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        fn = (EditText) findViewById(R.id.fn);
        email = (EditText) findViewById(R.id.em);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        erja3login = (TextView) findViewById(R.id.erja3login);
        erja3login.setOnClickListener(this);
        signup = (Button) findViewById(R.id.sup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.erja3login:
               startActivity(new Intent(this, Login.class));
                break;
            case R.id.sup:
                registeruser();
                break;
        }
    }


    private void registeruser() {
        String e = email.getText().toString().trim();
        String fname = fn.getText().toString().trim();
        String p = pass1.getText().toString().trim();
        String p2 = pass2.getText().toString().trim();
        if (e.isEmpty()) {
            email.setError("Email is required");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            email.setError("Please provide valide Email");
            email.requestFocus();
            return;

        }
        if (fname.isEmpty()) {
            fn.setError("full name is required");
            fn.requestFocus();
            return;
        }
        if (p.isEmpty()) {
            pass1.setError("Password is required");
            pass1.requestFocus();
            return;
        }
        if (p2.isEmpty()) {
            pass2.setError(" Confirmed Password is required");
            pass2.requestFocus();

            return;
        }


  mAuth.createUserWithEmailAndPassword(e,p)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                user user1=new user(fname,e,p);
                FirebaseDatabase.getInstance().getReference("user")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                        setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this,"user has been added",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(register.this,"user has not  been added",Toast.LENGTH_LONG).show();

                    }
                });
            }
            else
                Toast.makeText(register.this, "Failed to register", Toast.LENGTH_LONG).show();

        }
    });


}


                }





