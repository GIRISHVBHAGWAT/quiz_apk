package com.example.quiz_app;

import static com.example.quiz_app.R.array.chaturthi_dina;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Prayoga extends AppCompatActivity {

    int n = 0;
    private MediaRecorder mic;
    private String audiopath=null;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantra_play);

        getSupportActionBar().hide();
        TextView mantr_v;
        Button pre,play,next,plstop,record,stop;
        Spinner smain,subs;
        mantr_v=findViewById(R.id.mantra_v);
        pre = findViewById(R.id. button5);
        play = findViewById(R.id.button6);
        next = findViewById(R.id.button7);
        plstop = findViewById(R.id.button8);
        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        smain = findViewById(R.id.spinner);
        subs = findViewById(R.id.spinner2);
        TextView counter = findViewById(R.id.counter);



        stop.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        plstop.setVisibility(View.INVISIBLE);
//   Code starts  Opening Spinner Item to Array

// TextView Arrays are here
        String[] chaturthi_dina = getResources().getStringArray(R.array.chaturthi_dina_detail);
        String[][] texts = new String[][]{chaturthi_dina};


//   Spinner Text Are here (main Spinner And sub Spinner)
        int[] spinner_main = new int[]{R.array.Prayoga_Main_Spinner};
        int[] spinner_sub = new int[]{R.array.chaturthi_dina};

// Adapting spinner And Array
        ArrayAdapter<CharSequence> spins = ArrayAdapter.createFromResource(this,R.array.Prayoga_Main_Spinner,android.R.layout.simple_spinner_dropdown_item);
        spins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smain.setAdapter(spins);




// Main Spinner on Click Listner
        smain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//      Changing Sub Spinner Option by Array
                ArrayAdapter<CharSequence> spins2 = ArrayAdapter.createFromResource(Prayoga.this,spinner_sub[i],android.R.layout.simple_spinner_dropdown_item);
                spins2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subs.setAdapter(spins2);
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
                    audiopath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"recording.mp3";
                    mic = new MediaRecorder();
                    mic.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mic.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mic.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mic.setOutputFile(audiopath);



                    try {
                        mic.prepare();
                        mic.start();
                        Toast.makeText(Prayoga.this, "Recording Started", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    record.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.VISIBLE);
                }
                else{
                    ActivityCompat.requestPermissions(Prayoga.this,new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mic.stop();
                mic.release();
                Toast.makeText(Prayoga.this, "Stopped", Toast.LENGTH_SHORT).show();
                record.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);

            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spinner_posi = smain.getSelectedItemPosition();
                int spinner2_posi = subs.getSelectedItemPosition();

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

                try {
                    mp.setDataSource(audiopath);
                    mp.prepare();
                    mp.start();
                    Toast.makeText(Prayoga.this, "om", Toast.LENGTH_SHORT).show();
//                    mp.setLooping(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    int maxplay = 10;
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
                            play.setVisibility(View.VISIBLE);
                            record.setVisibility(View.VISIBLE);
                            plstop.setVisibility(View.INVISIBLE);

                        }
                    }
                });
                
                record.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.INVISIBLE);
                plstop.setVisibility(View.VISIBLE);
                play.setVisibility(View.INVISIBLE);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spinner_posi = smain.getSelectedItemPosition();
                int spinner2_posi = subs.getSelectedItemPosition();


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
                    record.setVisibility(View.VISIBLE);
                    plstop.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
    private boolean checkpermissions(){
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return first == PackageManager.PERMISSION_GRANTED && second == PackageManager.PERMISSION_GRANTED;
    }
}