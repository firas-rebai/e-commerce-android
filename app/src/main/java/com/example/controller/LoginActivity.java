package com.example.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.current;
import model.user;


public class LoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private TextView adminLink, notAdminLink, forgetPwd;

    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginButton = (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.login_email_input);
        adminLink = (TextView) findViewById(R.id.admin_panel_link);
        notAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        forgetPwd = (TextView) findViewById(R.id.forget_password_link);

        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Bientot disponible", Toast.LENGTH_SHORT).show();
            }
        });

        loadingBar = new ProgressDialog(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Administrateur");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });
    }


    private void loginUser() {
        String username = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Insert your username", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Insert your password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Connexion");
            loadingBar.setMessage("Veuiller patienté SVP");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            allowAccessToAccount(username, password);
        }
    }

    private void allowAccessToAccount(final String username, final String password) {
        final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference(parentDbName);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    user userDate = dataSnapshot.child(username).getValue(user.class);
                    if (userDate.getUsername().equals(username)) {
                        if (userDate.getPassword().equals(password)) {
                            if (parentDbName.equals("Admins")) {
                                Toast.makeText(LoginActivity.this, "Hello " + userDate.getUsername(), Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                current.currentUser = userDate;
                                current.isAdmin = true;
                                startActivity(intent);
                            } else if (parentDbName.equals("Users")) {
                                Toast.makeText(LoginActivity.this, "Hello " + userDate.getUsername(), Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                current.currentUser = userDate;
                                Intent intent = new Intent(LoginActivity.this, HomeBooksActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}