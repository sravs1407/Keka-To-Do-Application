package com.example.to_do_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button login;
        EditText userName;
        SharedPreferences sharedPreferences = getSharedPreferences("App",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        login = findViewById(R.id.LoginBtn);
        userName = findViewById(R.id.userNameText);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString().trim();
                if(!name.equals("")) {
                    editor.putString("userName", name);
                    editor.apply();
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Welcome, "+name, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), TaskActivity.class));
                }else{
                    userName.requestFocus();
                    userName.setError("Enter username");
//                    Toast.makeText(getApplicationContext(),"Enter UserName please!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}