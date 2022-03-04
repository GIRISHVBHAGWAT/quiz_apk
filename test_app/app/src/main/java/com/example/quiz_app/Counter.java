package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Counter extends AppCompatActivity {
int count_number = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Button plus = findViewById(R.id.plus);
        TextView count_v = findViewById(R.id.countv);

        getSupportActionBar().hide();


        count_v.setText(String.valueOf(count_number));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_number++;
                if(count_number==11){
                    count_number=0;
                    count_v.setText(String.valueOf(count_number));
                }
                else{
                    count_v.setText(String.valueOf(count_number));
                }
            }
        });
    }
}