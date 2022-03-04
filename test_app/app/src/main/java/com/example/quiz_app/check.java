package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class check extends AppCompatActivity {
    int i =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Button btn = findViewById(R.id.button);
        getSupportActionBar().hide();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    btn.setBackgroundColor(getResources().getColor(R.color.green));
                    i=i+1;

                }
                else {
                    btn.setBackgroundColor(getResources().getColor(R.color.black));
                    i=i-1;
                }



            }
        });
    }
}