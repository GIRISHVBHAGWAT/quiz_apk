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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class key_value extends Fragment {

    TextView tv;
    private static final int PERMISION_WRITE_EXTERNAL_STORAGE = 123;
    EditText et;
    EditText et2;
    EditText et3;
    Button bs;
    String writes,key,value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View w = inflater.inflate(R.layout.fragment_key_value, container, false);


        et = w.findViewById(R.id.editText1);
        et2 = w.findViewById(R.id.editText2);
        et3 = w.findViewById(R.id.editText3);




        et.requestFocus();
        et.setFocusableInTouchMode(true);







        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

                    key = et.getText().toString();
                    value = et2.getText().toString();
                    writes = key + "---" + value + "\\n"+"\n";
                    String file_name = et3.getText().toString();


                    if(et.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Write Key", Toast.LENGTH_SHORT).show();
                        et.requestFocus();
                        et.setFocusableInTouchMode(true);
                    }
                    else if(et2.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Write value", Toast.LENGTH_SHORT).show();
                        et2.requestFocus();
                        et2.setFocusableInTouchMode(true);
                    }
                    else{
                        saveFileK(writes,file_name);
                    }


                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISION_WRITE_EXTERNAL_STORAGE);
                }
            }
        });




        return w;

    }

    public void saveFileK(String write,String Filename){
        try {
            //To Save the File in a Directory called MyData in the root...
            //UnComment and Replace the Code
            String file_name = Filename;

            File myDir = new File(Environment.getExternalStorageDirectory() + "/MyData");
            myDir.mkdirs();
            String FileName = Filename + ".txt";

            File file = new File(myDir,FileName);


            FileOutputStream fos = new FileOutputStream(file,true);
            fos.write(write.getBytes());
            fos.close();

            et.setText("");
            et2.setText("");
            et.requestFocus();
            et.setFocusableInTouchMode(true);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



    }
}