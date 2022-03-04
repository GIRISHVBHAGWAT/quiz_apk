package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOError;
import java.util.ArrayList;

public class Read extends AppCompatActivity {

//    It's created for reading a quiz topic...
//    Iam getting a string from Main activity ..and making into to part questioin and answer
//    question will be set to A List view  answer will display in TextView .

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        getSupportActionBar().hide();

        ListView lv;

        lv=findViewById(R.id.lv);

        TextView textView = findViewById(R.id.tv2);
//  Information for quiz or requiz
        String hari = getIntent().getStringExtra("hari");

//  Condition to Check
        if(hari.equals("quiz")){
            String om = getIntent().getStringExtra("om");

            String[] arrayis = om.split("\n");
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();

//  Making saperete list for ques and ans
            for(String n : arrayis){
                arrayList.add(n.split("---")[0]);
                arrayList2.add(n.split("---")[1]);
            }

//  Setting ques to ListView Using Adapter
            ArrayAdapter<String> adp = new ArrayAdapter<String>(Read.this, android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
            lv.setAdapter(adp);

//  OnItem click listerner for chase a answer which store in another list

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView.setText(arrayList2.get(i));
                }
            });


        }


        else{
            String om = getIntent().getStringExtra("om");

            String[] arrayis = om.split("\n");
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();
            for(String n : arrayis){
                arrayList.add(n.split("---")[1]);
                arrayList2.add(n.split("---")[0]);
            }
            ArrayAdapter<String> adp = new ArrayAdapter<String>(Read.this, android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
            lv.setAdapter(adp);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView.setText(arrayList2.get(i));
                }
            });

        }







    }


}
