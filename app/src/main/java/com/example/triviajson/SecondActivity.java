package com.example.triviajson;


import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class SecondActivity extends AppCompatActivity{
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activtiy);
        imageView=findViewById(R.id.container);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("PassingUrls");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Glide.with(this).load(message).into(imageView);


    }
}
