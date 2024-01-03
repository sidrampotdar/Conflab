package com.example.conflab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ai.deepar.deepar_example.R;

public class RegisterActivity extends AppCompatActivity {

   EditText edtTxtUName,edtTxtREmail,edtTxtRPassword,edtTxtRPassword2;
   Button btnSignUp;
   TextView txtLogIn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtTxtUName=findViewById(R.id.edtTxtUName);
        edtTxtREmail=findViewById(R.id.edtTxtREmail);
        edtTxtRPassword =findViewById(R.id.edtTxtRPassword);;
        edtTxtRPassword2=findViewById(R.id.edtTxtPasswordx2);

        btnSignUp=findViewById(R.id.btnSignUp);

        txtLogIn=findViewById(R.id.txtLogin);


    }
}