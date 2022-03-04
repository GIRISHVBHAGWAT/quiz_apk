package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class rec_play extends AppCompatActivity {
    int pitch_n = 0;
    int speed_n = 0;
    private MediaRecorder mic;
    private String audiopath=null;
    private MediaPlayer mp;
    int check_playing = 0;
    public ArrayList<String> songs = new ArrayList<>();
    TextView mantr_v;
    Button play,plstop,record,stop,pause,speed,pitch;
    Spinner loop_count,ren_num;
    TextView counter;
    EditText file_name;
    ListView file_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_play);

        getSupportActionBar().hide();

        play = findViewById(R.id.button_play);
        plstop = findViewById(R.id.button_pstop);
        record = findViewById(R.id.button_record);
        stop = findViewById(R.id.button_rstop);
        pause = findViewById(R.id.button_pause);
        loop_count = findViewById(R.id.count_num);
        counter = findViewById(R.id.conut_text);
        ren_num = findViewById(R.id.ren_num);
        file_name = findViewById(R.id.file_name);
        file_list = findViewById(R.id.list_views);
        speed =findViewById(R.id.speed);
        pitch =findViewById(R.id.pitch);


        rec_play recPlay = new rec_play();
        recPlay.set_Invisibality(new View[]{plstop,stop,pause,speed,pitch});

        String[] ren_loops = new String[]{"1","2","3","4","5","6","7","7","8","9","10","11","12","13" ,
                "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
        "31","32","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};

//        Adapting loop counter spin
        ArrayAdapter<CharSequence> loop_c = ArrayAdapter.createFromResource(this,R.array.loop_count,android.R.layout.simple_spinner_dropdown_item);
        loop_c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loop_count.setAdapter(loop_c);


//        Adapting ren counter spin
        ArrayAdapter<CharSequence> ren_loop = new ArrayAdapter<CharSequence>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,ren_loops);
        ren_loop.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ren_num.setAdapter(ren_loop);



//      loop number Array
        int[] loop_numbers = new int[]{5,10,28,108,1008};


        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkpermissions() == true) {
                    //   File name text
                    String file_names = file_name.getText().toString();

                    audiopath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/" + file_names+"_"+String.valueOf(ren_loops[ren_num.getSelectedItemPosition()]+".mp3");

//                  record audio is a public func saved in java
                    record_Audio(audiopath);

                    set_visibality(new View[]{stop});
                    set_Invisibality(new View[]{record,play});

                }

                else {
                    ActivityCompat.requestPermissions(rec_play.this,new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                }
            }



            });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ren_num.setSelection(ren_num.getSelectedItemPosition()+1);
                mic.stop();
                mic.release();
                Toast.makeText(rec_play.this, "Stopped", Toast.LENGTH_SHORT).show();

                set_visibality(new View[]{record,play});
                stop.setVisibility(View.INVISIBLE);

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = new MediaPlayer();
                songs.clear();
                counter.setVisibility(View.INVISIBLE);
                file_list.setVisibility(View.VISIBLE);
                String audio_dir = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(audio_dir);
                String[] contents = file.list();

                for(String a: contents){
                    if(file_name.getText().toString().equals("")){
                        if(a.endsWith(".mp3")){
                            songs.add(a);
                        }
                    }
                    else {
                        if(a.startsWith(file_name.getText().toString())){
                            songs.add(a);
                        }
                    }

                }
                Collections.sort(songs);


                ArrayAdapter<String> list_file = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,songs);
                file_list.setAdapter(list_file);

                file_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        set_visibality(new View[]{counter,pause,plstop,pitch,speed});
                        set_Invisibality(new View[]{file_list,play,record,ren_num,loop_count});

                        String audio_dir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+songs.get(position);
                        player(audio_dir,loop_numbers[loop_count.getSelectedItemPosition()],counter,new View[]{play,record},new View[]{plstop,stop,pause,pitch,speed});



                    }
                });

            }
        });


        plstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(mp ==null)){
                    counter.setText(String.valueOf(0));
                    mp.stop();
                    mp.release();
                    speed.setText("speed");
                    pitch.setText("pitch");


                    set_visibality(new View[]{play,record,ren_num,loop_count});
                    set_Invisibality(new View[]{plstop,pause,speed,pitch});

                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    pause.setText("पुनरारंभ");

                }else{
                    mp.start();
                    pause.setText("विश्राम");
                }

            }
        });

        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    Mantra_play mantra_p = new Mantra_play();
                    float [] speed_points = new float[]{0.90f,1.0f,1.10f,1.30f};
                    if(speed_n<speed_points.length){
                        mantra_p.set_speed(mp,speed_points[speed_n]);
                        speed.setText(String.valueOf(speed_points[speed_n]));
                        speed_n++;

                    }
                    else{
                        speed_n=0;
                    }
                }
            }
        });

        pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    Mantra_play mantra_p = new Mantra_play();
                    float [] pich_points = new float[]{0.90f,1.0f,1.10f};
                    if(pitch_n<pich_points.length){
                        mantra_p.set_pitch(mp,pich_points[pitch_n]);
                        pitch.setText(String.valueOf(pich_points[pitch_n]));
                        pitch_n++;
                    }
                    else{
                        pitch_n=0;
                    }
                }
            }
        });


    }

//    Checkin User permissins
    private boolean checkpermissions(){
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return first == PackageManager.PERMISSION_GRANTED && second == PackageManager.PERMISSION_GRANTED;
    }
//    Recording Audio

    public void record_Audio(String audiopath){
        mic = new MediaRecorder();
        mic.setAudioSource(MediaRecorder.AudioSource.MIC);
        mic.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mic.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mic.setOutputFile(audiopath);
        try {
            mic.prepare();
            mic.start();
            Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    Playing Audio

    public void player (String audiopath, int loop_time, TextView counter, View[] visible, View[] invisible){

        try {
            mp.setDataSource(audiopath);
            mp.prepare();
            mp.start();
            Toast.makeText(getApplicationContext(), "om", Toast.LENGTH_SHORT).show();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                int maxplay = loop_time;
                int played = 1;
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(played<maxplay){
                        counter.setText(String.valueOf(played));
                        played++;
                        mp.start();
                        mp.seekTo(0);
                        if(played>99){
                            counter.setTextSize(100);
                        }
                        else{
                            counter.setTextSize(200);
                        }
                    }
                    else{
                        played=1;
                        mp.seekTo(0);
                        counter.setText(String.valueOf(0));
                        mp.stop();
                        mp.release();
                        speed.setText("speed");
                        pitch.setText("pitch");
                        for(View a: visible){
                            a.setVisibility(View.VISIBLE);
                        }
                        for(View a: invisible){
                            a.setVisibility(View.INVISIBLE);
                        }

                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    Visible INVISIBLE View

    public void set_visibality(View[] arr){
        for(View a: arr){
            a.setVisibility(View.VISIBLE);
        }
    }
    public void set_Invisibality(View[] arr){
        for(View a: arr){
            a.setVisibility(View.GONE);
        }
    }





}



