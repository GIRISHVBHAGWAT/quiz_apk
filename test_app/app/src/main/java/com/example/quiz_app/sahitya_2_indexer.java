package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class sahitya_2_indexer extends AppCompatActivity {
//    All_in_one_1.0
    int Color = 0;
    ImageButton gadya,champu,tarka,vyakaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahitya_2_indexer);
        getSupportActionBar().hide();
        gadya=findViewById(R.id.quiz);
        champu=findViewById(R.id.sahitya_padya);
        tarka=findViewById(R.id.body);
        vyakaran=findViewById(R.id.tree);

        String gadya1= getResources().getString(R.string.indra_sandesha);
        String gadya2= getResources().getString(R.string.dushta_damanam);
        String gadya3= getResources().getString(R.string.kalivimarshanam);
        String gadya4= getResources().getString(R.string.gugu_pranati);
        String padya1 = getResources().getString(R.string.shishyatrayam);
        String padya2 = getResources().getString(R.string.vikramoudaryam);
        String padya3 = getResources().getString(R.string.gograhanam);
        String samas = getResources().getString(R.string.samasa);
        String samas2 = getResources().getString(R.string.samasa2);
        String samas3 = getResources().getString(R.string.samasa3);
        String samas4 = getResources().getString(R.string.samasa4);
        String samas5 = getResources().getString(R.string.samasa5);
        String samas6 = getResources().getString(R.string.samasa6);
        String paryay = getResources().getString(R.string.paryayapadam2);
        String paryay2 = getResources().getString(R.string.paryayapadam3);
        String paryay3 = getResources().getString(R.string.paryayapadam4);

        String vya1 = getResources().getString(R.string.abhyasa1);
        String vya2 = getResources().getString(R.string.abhyasa2);
        String vya3 = getResources().getString(R.string.abhyasa3);
        String vya4 = getResources().getString(R.string.abhyasa4);
        String vya5 = getResources().getString(R.string.abhyasa5);
        String vya6 = getResources().getString(R.string.abhyasa6);




        gadya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sahitya_2_indexer.this,MainActivity.class);
                intent.putExtra("sahi_padya",getResources().getStringArray(R.array.sahity2_padya_gadya));
                intent.putExtra("strings",new String[]{gadya1,gadya2,gadya3,gadya4,padya1,padya2,padya3,samas,samas2,samas3,samas4,samas5,samas6,paryay,paryay2,paryay3});
                startActivity(intent);
            }
        });

        champu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sahitya_2_indexer.this,MainActivity.class);
                intent.putExtra("sahi_padya",getResources().getStringArray(R.array.sahity2_padya_gadya));
                intent.putExtra("strings",new String[]{gadya1,gadya2,gadya3,gadya4,padya1,padya2,padya3,samas,paryay});
                startActivity(intent);
            }
        });


        tarka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sahitya_2_indexer.this,MainActivity.class);
                intent.putExtra("sahi_padya",getResources().getStringArray(R.array.sahity2_padya_gadya));
                intent.putExtra("strings",new String[]{gadya1,gadya2,gadya3,gadya4,padya1,padya2,padya3,samas,paryay});
                startActivity(intent);
            }
        });

        vyakaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sahitya_2_indexer.this,MainActivity.class);
                intent.putExtra("sahi_padya",getResources().getStringArray(R.array.sahitya_vya_abhyas));
                intent.putExtra("strings",new String[]{vya1,vya2,vya3,vya4,vya5,vya6});
                startActivity(intent);
            }
        });


    }
}