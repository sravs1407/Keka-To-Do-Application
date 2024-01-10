package com.example.to_do_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.to_do_application.Register;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("App", MODE_PRIVATE);
                String name = sharedPreferences.getString("userName","_");

                if(name.equals("_")) {
                    startActivity(new Intent(getApplicationContext(), Register.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), TaskActivity.class));
                    finish();
                }
            }
        },2000);



    }
}