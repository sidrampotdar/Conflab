package com.example.conflab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ai.deepar.deepar_example.R;

public class LoginActivity extends AppCompatActivity {
EditText edtTxtEmail,edtTxtPassword;
Button btnLonIn;
TextView txtSignUp;
FirebaseAuth auth;
    android.app.ProgressDialog progressDialog;
String emailRegex="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        edtTxtEmail=findViewById(R.id.edtTxtLogEmail);
        edtTxtPassword=findViewById(R.id.edtTxtLogPassword);

        btnLonIn=findViewById(R.id.btnLonIn);

        txtSignUp=findViewById(R.id.txtSignUP);

        auth=FirebaseAuth.getInstance();

        btnLonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=edtTxtEmail.getText().toString();
                password=edtTxtPassword.getText().toString();
//                if(email.isEmpty()||password.isEmpty()){
//                    Toast.makeText(LoginActivity.this,"NULL Values",Toast.LENGTH_LONG);
//
//                }
                if(TextUtils.isEmpty(email)){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Invalid Email",Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(password)){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Invalid Password",Toast.LENGTH_LONG).show();
                }
              else  if(!email.matches(emailRegex)){
                    progressDialog.dismiss();
                  edtTxtEmail.setError("Invalid Email");

                }

                else{
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                try{
                                    progressDialog.show();
                                    startActivity(new Intent(LoginActivity.this,MainActivity2.class));
                                      finish();
                                } catch (Exception e) {
                                    Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                                }
                            }else{
                                Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });

txtSignUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        finish();
    }
});


    }
}