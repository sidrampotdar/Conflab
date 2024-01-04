package com.example.conflab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import ai.deepar.deepar_example.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText edtTxtUName,edtTxtREmail,edtTxtRPassword,edtTxtRPassword2;
    Button btnSignUp;
    TextView txtLogIn;
    CircleImageView circleImageView;
    Uri imageURI;
    String emailRegex="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String imageUri;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;


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
        circleImageView=findViewById(R.id.profilerg0);
        txtLogIn=findViewById(R.id.txtLogin);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtTxtUName.getText().toString();
                String email=edtTxtREmail.getText().toString();
                String password=edtTxtRPassword.getText().toString();
                String cPassword=edtTxtRPassword2.getText().toString();
                String status ="Hello World...";
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(cPassword)){

                    Toast.makeText(RegisterActivity.this, "Null Data", Toast.LENGTH_SHORT).show();

                } else if(!email.matches(emailRegex)){
                    edtTxtREmail.setError("Invalid Email...");
                    Toast.makeText(RegisterActivity.this, "Invalid Email...", Toast.LENGTH_SHORT).show();
                } else if (!password.matches(cPassword)){
                    edtTxtRPassword2.setText("Password does NOT match...");
                } else {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String id=task.getResult().getUser().getUid();
                                DatabaseReference reference=database.getReference().child("user").child(id);
                                StorageReference storageReference=storage.getReference().child("Upload").child(id);

                                if(imageURI!=null){
                                    storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if(task.isSuccessful()){
                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imageUri=uri.toString();
                                                        Users users=new Users(id,name,email,password,imageUri,status);
                                                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    Intent intent =new Intent(RegisterActivity.this,MainActivity2.class);
                                                                    startActivity(intent);
                                                                    finish();

                                                                }else{
                                                                    Toast.makeText(RegisterActivity.this, "Error In Creating User....", Toast.LENGTH_SHORT).show();

                                                                }
                                                            }
                                                        });

                                                    }
                                                });
                                            }
                                        }
                                    });
                                }else {
                                    String status ="Hello World...";
                                    imageUri="https://firebasestorage.googleapis.com/v0/b/conflab-61ecb.appspot.com/o/Upload%2Fimg.jpeg?alt=media&token=8671aa1e-a38a-4712-ad22-5877f43e03fa";
                                    Users users=new Users(id,name,email,password,imageUri,status);

                                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Intent intent =new Intent(RegisterActivity.this,MainActivity2.class);
                                                Toast.makeText(RegisterActivity.this, "Signed In Successfully...", Toast.LENGTH_SHORT).show();

                                                startActivity(intent);
                                                finish();

                                            }else{
                                                Toast.makeText(RegisterActivity.this, "Error In Creating User....", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                                }
                            }else{
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });





        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select picture"),10);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10) {
            if (data != null) {
                imageURI = data.getData();
                circleImageView.setImageURI(imageURI);
            }
        }
    }
}

