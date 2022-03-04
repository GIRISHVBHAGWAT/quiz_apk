package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    private static final int PERMISION_WRITE_EXTERNAL_STORAGE = 123;
    Button write,key_vlaue;
    LinearLayout linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        write = findViewById(R.id.button3);
        key_vlaue = findViewById(R.id.button2);
        linear = findViewById(R.id.linear2);
        if(savedInstanceState == null) {
            text_writer textWriter = new text_writer();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linear2,textWriter);
            transaction.commit();
            color_changer(key_vlaue,write);

        }


        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_writer writeF = new text_writer();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear2,writeF);
                transaction.commit();
                color_changer(key_vlaue,write);
            }
        });
        key_vlaue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key_value keyF = new key_value();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear2,keyF);
                transaction.commit();
                color_changer(write,key_vlaue);
            }
        });


    }

    public void color_changer(Button a, Button b){
        a.setBackgroundColor(getResources().getColor(R.color.black));
        a.setTextColor(getResources().getColor(R.color.white));

        b.setBackgroundColor(getResources().getColor(R.color.white));
        b.setTextColor(getResources().getColor(R.color.black));
    }


}