package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class open_file extends AppCompatActivity {
private MediaPlayer msp;
public   ArrayList<CharSequence> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);

        getSupportActionBar().hide();

        Button go_to = findViewById(R.id.gofile);
        ListView lst = findViewById(R.id.lst);
        Button stop = findViewById(R.id.stop);

        stop.setVisibility(View.INVISIBLE);



        go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkper()){
                    songs.clear();
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    File dir_path = new File(path);
                    String[] files = dir_path.list();
                    for(String a: files){
                        if(a.endsWith(".mp3")){
                            songs.add(a);
                        }

                    }

                    ArrayAdapter<CharSequence> ost = new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_list_item_1,songs);
                    lst.setAdapter(ost);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            msp = new MediaPlayer();
                            String song_path = Environment.getExternalStorageDirectory().getAbsolutePath();
                            try {
                                msp.setDataSource(song_path+"/"+songs.get(position));
                                msp.prepare();
                                msp.start();
                                go_to.setVisibility(View.INVISIBLE);
                                stop.setVisibility(View.VISIBLE);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }else{
                    ActivityCompat.requestPermissions(open_file.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }




            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msp.isPlaying()){
                    msp.stop();
                    go_to.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.INVISIBLE);
                }

            }
        });


    }
    private boolean checkper(){
        int write = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return write== PackageManager.PERMISSION_GRANTED;
    }
}