package com.example.quiz_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class text_writer extends Fragment {
static EditText writeit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_text_writer, container, false);
        writeit = v.findViewById(R.id.write);
        EditText file_name = v.findViewById(R.id.filename);


        writeit.requestFocus();
        writeit.setFocusableInTouchMode(true);
        writeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

                    String texts = writeit.getText().toString()+"\\n"+"\n";
                    String file_n = file_name.getText().toString();
                    if(writeit.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Write Key", Toast.LENGTH_SHORT).show();
                        writeit.requestFocus();
                        writeit.setFocusableInTouchMode(true);
                    }else{

                        saveFileT(texts,file_n);

                    }


                }
                else{
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                }

            }

        });

        return v;
    }

    public void saveFileT(String write,String Filename){
        try {
            String file_name = Filename;

            File myDir = new File(Environment.getExternalStorageDirectory() + "/MyData");
            myDir.mkdirs();
            String FileName = Filename + ".txt";

            File file = new File(myDir,FileName);


            FileOutputStream fos = new FileOutputStream(file,true);
            fos.write(write.getBytes());
            fos.close();
            writeit.setText("");
            writeit.requestFocus();
            writeit.setFocusableInTouchMode(true);




        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



    }
}