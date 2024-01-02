package com.example.conflab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

import ai.deepar.deepar_example.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//       new  Handler().postDelayed(new Runnable() {
//           @Override
//           public void run() {
//               startActivity(new Intent(SplashActivity.this,MainActivity.class));
//               finish();
//           }
//       },3000);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Kiran");
        user.put("last", "Kamate");
        user.put("born", 2005);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                     //   Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(SplashActivity.this,"Successfull",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                 //       Log.w(TAG, "Error adding document", e);
                        Toast.makeText(SplashActivity.this,"Failed",Toast.LENGTH_LONG).show();
                    }
                });
    }
}