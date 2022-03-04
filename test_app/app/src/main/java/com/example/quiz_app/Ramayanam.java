package com.example.quiz_app;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

public class Ramayanam extends AppCompatActivity {
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
       String[] agnirnah = getResources().getStringArray(R.array.agnirnah);
       String[] rudhyasma = getResources().getStringArray(R.array.rudhyasma);

        String[][] texts = new String[][]{agnirnah,rudhyasma};

//        counter.setText(String.valueOf(udakashanti.length));
        String[] rudhyasma_index = new String[rudhyasma.length];
        for(int i=0; i<rudhyasma.length; i++){
            rudhyasma_index[i]=rudhyasma[i].split(" ")[0]+" "+rudhyasma[i].split(" ")[1]+" "+rudhyasma[i].split(" ")[2];
        }

        String[] agnirnah_index = new String[agnirnah.length];
        for(int i=0; i<agnirnah.length; i++){
            agnirnah_index[i]=agnirnah[i].split(" ")[0]+" "+agnirnah[i].split(" ")[1]+" "+agnirnah[i].split(" ")[2];
        }


//   Spinner Text Are here (main Spinner And sub Spinner)

        int[] spinner_main = new int[]{R.array.Udakashanti_index};
        int[] spinner_sub = new int[]{R.array.agnirnah,R.array.rudhyasma};

// Adapting spinner And Array


        ArrayAdapter<CharSequence> spins = ArrayAdapter.createFromResource(this,spinner_main[0],android.R.layout.simple_spinner_dropdown_item);
        spins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smain.setAdapter(spins);




// Main Spinner on Click Listner
        smain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0){
                    ArrayAdapter<CharSequence> udak_spin = new ArrayAdapter<CharSequence>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,agnirnah_index);
                    subs.setAdapter(udak_spin);
                }
                else if(i==1){
                    ArrayAdapter<CharSequence> sapta_spin = new ArrayAdapter<CharSequence>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,rudhyasma_index);
                    subs.setAdapter(sapta_spin);
                }
                else {

//      Changing Sub Spinner Option by Array
                    ArrayAdapter<CharSequence> spins2 = ArrayAdapter.createFromResource(Ramayanam.this, spinner_sub[i], android.R.layout.simple_spinner_dropdown_item);
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
                    audiopath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"recording.mp3";
                    mic = new MediaRecorder();
                    mic.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mic.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mic.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mic.setOutputFile(audiopath);



                    try {
                        mic.prepare();
                        mic.start();
                        Toast.makeText(Ramayanam.this, "Recording Started", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    record.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.VISIBLE);
                }
                else{
                    ActivityCompat.requestPermissions(Ramayanam.this,new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mic.stop();
                mic.release();
                Toast.makeText(Ramayanam.this, "Stopped", Toast.LENGTH_SHORT).show();
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
                n=spinner2_posi;
                if(n>0){
                    n--;
                }
                else{
                    n=texts[spinner2_posi].length-1;
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
                    Toast.makeText(Ramayanam.this, "om", Toast.LENGTH_SHORT).show();
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
                mp.setScreenOnWhilePlaying(true);

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