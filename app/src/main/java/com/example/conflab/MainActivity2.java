package com.example.conflab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import ai.deepar.deepar_example.R;

public class MainActivity2 extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()==null){
            startActivity(new Intent(MainActivity2.this,LoginActivity.class));
            finish();
        }

    }
}