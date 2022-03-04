package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class Mantra_play extends AppCompatActivity {
int n = 0;
private MediaRecorder mic;
private String audiopath=null;
private MediaPlayer mp;
    int check_playing = 0;

    rec_play recPlay = new rec_play();

    Button pre,play,next,plstop,record,stop,plpause;
    Spinner smain,subs,loop_count;
    TextView counter;
    int[] loop_numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantra_play);
        getSupportActionBar().hide();
        TextView mantr_v;
        mantr_v=findViewById(R.id.mantra_v);
        pre = findViewById(R.id. button5);
        play = findViewById(R.id.button6);
        next = findViewById(R.id.button7);
        plstop = findViewById(R.id.button8);
        plpause = findViewById(R.id.plpause);
        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        smain = findViewById(R.id.spinner);
        subs = findViewById(R.id.spinner2);
        loop_count = findViewById(R.id.loop_count);
        counter = findViewById(R.id.counter);


        stop.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        plstop.setVisibility(View.INVISIBLE);
        plpause.setVisibility(View.INVISIBLE);
//   Code starts  Opening Spinner Item to Array

// TextView Arrays are here
        String[] mangala_detail = getResources().getStringArray(R.array.mangala_detail);
        String[] lakshmipanch_detail = getResources().getStringArray(R.array.lakshminpanch_detail);
        String[] saptasha11 = getResources().getStringArray(R.array.sapta_11);
        String[] asheerman = getResources().getStringArray(R.array.asheermantra_detail);
        String [] ruchams = getResources().getStringArray(R.array.rucham);
        String [] navos = getResources().getStringArray(R.array.navo_detail);
        String[] udakashanti = getResources().getStringArray(R.array.Udakashanti_detail);
        String[] shishupalavadha = getResources().getString(R.string.shivadha).split("\n");
        String[][] texts = new String[][]{mangala_detail,lakshmipanch_detail,saptasha11,asheerman,ruchams,navos,udakashanti,shishupalavadha};

        for (int i = 0; i < shishupalavadha.length; i++) {
            String rs = shishupalavadha[i].replace("---", "\n");
            shishupalavadha[i] = rs;

        }

//        counter.setText(String.valueOf(udakashanti.length));
        String[] udak_index = spinn_indexer(udakashanti);
        String[] shishupal_index = spinn_indexer(shishupalavadha);
        String[] sapta_index = spinn_indexer(saptasha11);



//   Spinner Text Are here (main Spinner And sub Spinner)

        int[] spinner_main = new int[]{R.array.Main_spinner};
        int[] spinner_sub = new int[]{R.array.mangala,R.array.lakshminpanch,R.array.saptashati_11,R.array.asheermantra,R.array.rucham,R.array.navo};

// Adapting spinner And Array


        ArrayAdapter<CharSequence> spins = ArrayAdapter.createFromResource(this,R.array.Main_spinner,android.R.layout.simple_spinner_dropdown_item);
        spins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smain.setAdapter(spins);

//        Adapting loop counter spin
        ArrayAdapter<CharSequence> loop_c = ArrayAdapter.createFromResource(this,R.array.loop_count,android.R.layout.simple_spinner_dropdown_item);
        spins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loop_count.setAdapter(loop_c);

//      loop number Array
        loop_numbers = new int[]{5,10,28,108,1008};



// Main Spinner on Click Listner
        smain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==6){
                    ArrayAdapter<CharSequence> udak_spin = new ArrayAdapter<CharSequence>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,udak_index);
                    subs.setAdapter(udak_spin);
                }
                else if(i==2){
                    ArrayAdapter<CharSequence> sapta_spin = new ArrayAdapter<CharSequence>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,sapta_index);
                    subs.setAdapter(sapta_spin);
                }
                else if(i==7){
                    ArrayAdapter<CharSequence> shishu_spin = new ArrayAdapter<CharSequence>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,shishupal_index);
                    subs.setAdapter(shishu_spin);
                }
                else {

//      Changing Sub Spinner Option by Array
                    ArrayAdapter<CharSequence> spins2 = ArrayAdapter.createFromResource(Mantra_play.this, spinner_sub[i], android.R.layout.simple_spinner_dropdown_item);
                    spins2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    subs.setAdapter(spins2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        subs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int spinner_posi = smain.getSelectedItemPosition();
                int spinner2_posi = subs.getSelectedItemPosition();


                mantr_v.setText(texts[spinner_posi][spinner2_posi]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkpermissions()==true){
                    audiopath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"om.mp3";
                    mic = new MediaRecorder();
                    mic.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mic.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mic.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mic.setOutputFile(audiopath);



                    try {
                        mic.prepare();
                        mic.start();
                        Toast.makeText(Mantra_play.this, "Recording Started", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recPlay.set_visibality(new View[]{stop});
                    recPlay.set_Invisibality(new View[]{record,play,loop_count});

                }
                else{
                    ActivityCompat.requestPermissions(Mantra_play.this,new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mic.stop();
                mic.release();
                Toast.makeText(Mantra_play.this, "Stopped", Toast.LENGTH_SHORT).show();
                recPlay.set_visibality(new View[]{record,play});
                recPlay.set_Invisibality(new View[]{stop});


            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spinner_posi = smain.getSelectedItemPosition();
                int spinner2_posi = subs.getSelectedItemPosition();
                n=spinner2_posi;
                if(n>0){
                    n--;
                }

                else{
                    n=texts[spinner_posi].length-1;
                }
                subs.setSelection(n);
                mantr_v.setText(texts[spinner_posi][spinner2_posi]);

            }
        });
//
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = new MediaPlayer();
                music_player(mp,audiopath);



                recPlay.set_visibality(new View[]{plpause,plstop});
                recPlay.set_Invisibality(new View[]{record,stop,play,loop_count});


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spinner_posi = smain.getSelectedItemPosition();
                int spinner2_posi = subs.getSelectedItemPosition();
                n=spinner2_posi;


                if(n<texts[spinner_posi].length-1){
                    n++;
                }
                else{
                    n=0;
                }
                subs.setSelection(n);
                mantr_v.setText(texts[spinner_posi][spinner2_posi]);
            }
        });
//
        plstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(mp ==null)){
                    counter.setText(String.valueOf(0));
                    mp.stop();
                    mp.release();
                    play.setVisibility(View.VISIBLE);
                    loop_count.setVisibility(View.VISIBLE);
                    record.setVisibility(View.VISIBLE);
                    plstop.setVisibility(View.INVISIBLE);
                    plpause.setVisibility(View.INVISIBLE);
                }
            }
        });
        plpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    plpause.setText("Play");

                }
                else{
                    mp.start();
                    plpause.setText("Pause");

                }
            }
        });



    }
    public boolean checkpermissions(){
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
    return first == PackageManager.PERMISSION_GRANTED && second == PackageManager.PERMISSION_GRANTED;
    }
    public  String[] spinn_indexer(String[] udakashanti){
        String[] udak_index = new String[udakashanti.length];

        for(int i=0; i<udakashanti.length; i++){
            udak_index[i]=udakashanti[i].split(" ")[0]+" "+udakashanti[i].split(" ")[1]+" "+udakashanti[i].split(" ")[2];
        }
        return udak_index;
    }
    public void music_player(MediaPlayer mp ,String audiopath){
        try {
            mp.setDataSource(audiopath);
            mp.prepare();
            mp.start();




            Toast.makeText(getApplicationContext(), "om", Toast.LENGTH_SHORT).show();
//                    mp.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            int maxplay = loop_numbers[loop_count.getSelectedItemPosition()];
            int played = 1;
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(played<maxplay){
                    counter.setText(String.valueOf(played));
                    played++;
                    mp.start();
                    mp.seekTo(0);

                }

                else{
                    played=1;
                    mp.seekTo(0);
                    counter.setText(String.valueOf(0));
                    mp.stop();
                    mp.release();
                    recPlay.set_visibality(new View[]{play,loop_count,record});
                    recPlay.set_Invisibality(new View[]{plstop,plpause});

                }
            }
        });



    }
    public void set_speed(MediaPlayer mp,float speed){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PlaybackParams params = new PlaybackParams();
            params.setSpeed(speed);

            mp.setPlaybackParams(params);
        }
    }
    public void set_pitch(MediaPlayer mp,float pitch){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PlaybackParams params = new PlaybackParams();
            params.setPitch(pitch);

            mp.setPlaybackParams(params);
        }
    }

}