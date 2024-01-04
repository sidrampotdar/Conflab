package com.example.conflab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ai.deepar.deepar_example.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    String recImg,recUid,recName,senderId;
    EditText edtTxtMsg;
    TextView txtRecName;
CircleImageView profilPicChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        recName=getIntent().getStringExtra("uName");
        recImg=getIntent().getStringExtra("recImg");
        recUid=getIntent().getStringExtra("UId");

        profilPicChat=findViewById(R.id.profilPicChat);
        edtTxtMsg=findViewById(R.id.edtTxtMsg);
        txtRecName=findViewById(R.id.edtTxtRecName);

        Picasso.get().load(recImg).into(profilPicChat);

        txtRecName.setText(""+recName);








    }
}