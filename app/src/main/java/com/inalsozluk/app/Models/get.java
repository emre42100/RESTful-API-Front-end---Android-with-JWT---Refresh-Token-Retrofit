package com.inalsozluk.app.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class get extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ge();

    }

    public String ge() {


        sharedPreferences = getSharedPreferences("gel", Context.MODE_PRIVATE);
        sharedPreferences.getString("geldim", "geldim");
        System.out.println(sharedPreferences.getString("geldim", "geldim"));
        String fd = sharedPreferences.getString("geldim", "geldim");
        return fd;


    }
}

