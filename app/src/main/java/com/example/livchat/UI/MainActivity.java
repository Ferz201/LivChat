package com.example.livchat.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.example.livchat.R;

import java.util.Objects;

/*
    This programms it's repository github
    123
 */


public class MainActivity extends AppCompatActivity {

    Context ctx;
    private Fragment fMain = new FragmentMain();
    static Boolean fragm = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
        ctx = this;

        if(!fragm) {
            FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.fragment, fMain).commit();
            fragm = true;
        }
    }
}
