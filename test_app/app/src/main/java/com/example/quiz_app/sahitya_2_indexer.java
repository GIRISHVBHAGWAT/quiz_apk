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

        String tarka_prame1 = getResources().getString(R.string.prameya1);
        String tarka_prame2 = getResources().getString(R.string.prameya2);
        String tarka_prame3 = getResources().getString(R.string.prameya3);
        String tarka_prame4 = getResources().getString(R.string.prameya4);
        String tarka_prame5 = getResources().getString(R.string.prameya5);
        String tarka_prame6 = getResources().getString(R.string.prameya6);
        String tarka_prame7 = getResources().getString(R.string.prameya7);
        String tarka_prame8 = getResources().getString(R.string.prameya8);
        String tarka_prame9 = getResources().getString(R.string.prameya9);
        String tarka_prame10 = getResources().getString(R.string.prameya10);
        String tarka_prame11 = getResources().getString(R.string.prameya11);
        String tarka_prame12 = getResources().getString(R.string.prameya12);
        String tarka_prame13 = getResources().getString(R.string.prameya13);




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
                intent.putExtra("sahi_padya",getResources().getStringArray(R.array.tarka_prame));
                intent.putExtra("strings",new String[]{tarka_prame1,tarka_prame2,tarka_prame3,tarka_prame4,tarka_prame5,tarka_prame6,tarka_prame7,tarka_prame8,tarka_prame9,tarka_prame10,tarka_prame11,tarka_prame12,tarka_prame13});
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