package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int ix = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        ImageButton imb = findViewById(R.id.imageButton);

        Spinner spinner;
        spinner=findViewById(R.id.spin);
// Action Bar hiding
        getSupportActionBar().hide();


// Making list for question And Answer
        ArrayList<String> rs = new ArrayList<String>();
        ArrayList<String> rs2 = new ArrayList<String>(rs.size());

//  string file opening from string.xml
        String str = getResources().getString(R.string.body);
        String str2 = getResources().getString(R.string.tree);
        String str3 = getResources().getString(R.string.pattanam);
        String str4 = getResources().getString(R.string.animal);
        String str5 = getResources().getString(R.string.wear);
        String str6 = getResources().getString(R.string.verb);
        String str7 = getResources().getString(R.string.vyakaranam);
        String str8 = getResources().getString(R.string.tarkaha);
        String str9 = getResources().getString(R.string.vibhakti_prakaranam);
        String str10 = getResources().getString(R.string.samasa);



        ArrayAdapter<CharSequence> odp = ArrayAdapter.createFromResource(this,R.array.omx , android.R.layout.simple_spinner_dropdown_item);
        odp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(odp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rs.clear();
                rs2.clear();

                String [] name_of_string = new String[] {str,str2,str3,str4,str5,str6,str7,str8,str9,str10};
                generate_quiz_2(rs,rs2,name_of_string[i]);


                imb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ix==0){
                            generate_quiz(rs,rs2,name_of_string[i]);
                            ix=ix+1;
                        }
                        else if(ix==1){
                            generate_quiz_2(rs,rs2,name_of_string[i]);
                            ix=ix-1;
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

    }

    public void generate_quiz(ArrayList<String> rs, ArrayList<String>rs2, String str){
        TextView tv;
        Button b1,b2,b3,b4,b5;
        Button read;

        tv=findViewById(R.id.textView2);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        read=findViewById(R.id.read);

        read.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "hari om", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        b1.setClickable(true);
        Button[] butto = new Button[] {b1,b2,b3,b4};

        for(Button x : butto){
            x.setVisibility(View.VISIBLE);
            x.setBackgroundColor(getResources().getColor(R.color.yellow));
            x.setTextColor(getResources().getColor(R.color.myblack));

        }

// next swich butttons implementation
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate_quiz(rs,rs2,str);

            }
        });
        //  Read button to go new Screen to Read
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Read.class);
                intent.putExtra("hari","quiz");
                intent.putExtra("om",str);
                startActivity(intent);
            }
        });




        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);


        String[] qs = str.split("\n");




// adding element to list
        for(int i=0; i<qs.length-2; i++){
            rs.add(qs[i].split("---")[0]);
            rs2.add(qs[i].split("---")[1]);
        }


        Random qus = new Random();
        int qusn = qus.nextInt(rs.size());





//  question
        tv.setText(rs.get(qusn));


        int an2 = qus.nextInt(rs2.size());
        int an3 = qus.nextInt(rs2.size());
        int an4 = qus.nextInt(rs2.size());

        String ans1 = rs2.get(qusn);
        String ans2 = rs2.get(an2);
        String ans3 = rs2.get(an3);
        String ans4 = rs2.get(an4);

//  Mix option
        String[] opt = new String[]{ans1,ans2,ans3,ans4};
        String[] opt1 = new String[]{ans2,ans3,ans4,ans1};
        String[] opt2 = new String[]{ans3,ans4,ans1,ans2};
        String[] opt3 = new String[]{ans4,ans1,ans2,ans3};

        String[][] options = new String[][]{opt,opt1,opt2,opt3};
        int opti = qus.nextInt(opt.length);

        String[] answers = options[opti];
        int indexa =Arrays.asList(answers).indexOf(ans1);
        Button[] buttons = new Button[]{b1,b2,b3,b4};

        b1.setText(answers[0]);
        b2.setText(answers[1]);
        b3.setText(answers[2]);
        b4.setText(answers[3]);

//        check_Answer(b1,b2,b3,b4,rs2.get(qusn));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            b1.setBackgroundColor(R.color.red);
                check_Answer(b1,b2,b3,b4,rs2.get(qusn),buttons,indexa);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b2,b3,b4,b1,rs2.get(qusn),buttons,indexa);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b3,b2,b1,b4,rs2.get(qusn),buttons,indexa);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b4,b2,b3,b1,rs2.get(qusn),buttons,indexa);

            }
        });


    }


    public void generate_quiz_2(ArrayList<String> rs, ArrayList<String>rs2, String str){
        TextView tv;
        Button b1,b2,b3,b4,b5;
        Button read = findViewById(R.id.read);

        tv=findViewById(R.id.textView2);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        Button[] butto = new Button[] {b1,b2,b3,b4};

        for(Button x : butto){
            x.setVisibility(View.VISIBLE);
            x.setBackgroundColor(getResources().getColor(R.color.yellow));
            x.setTextColor(getResources().getColor(R.color.myblack));

        }



// next swich butttons implementation
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate_quiz_2(rs,rs2,str);
            }
        });

//  Read button to go Another Screen
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Read.class);
                intent.putExtra("hari","requiz");
                intent.putExtra("om",str);
                startActivity(intent);
            }
        });


        b2.setClickable(true);
        b3.setClickable(true);
//            d.setEnabled(false);
        b4.setClickable(true);

// String opening from string.xml
//        String str = getResources().getString(R.string.vyakaranam);

        String[] qs = str.split("\n");





// adding element to list
        for(int i=0; i<qs.length-2; i++){
            rs.add(qs[i].split("---")[0]);
            rs2.add(qs[i].split("---")[1]);
        }


        Random qus = new Random();
        int qusn = qus.nextInt(rs2.size());

//  question
        tv.setText(rs2.get(qusn));

//  answers


        int an2 = qus.nextInt(rs.size());
        int an3 = qus.nextInt(rs.size());
        int an4 = qus.nextInt(rs.size());

        String ans1 = rs.get(qusn);
        String ans2 = rs.get(an2);
        String ans3 = rs.get(an3);
        String ans4 = rs.get(an4);

//  Mix option
        String[] opt = new String[]{ans1,ans2,ans3,ans4};
        String[] opt1 = new String[]{ans2,ans3,ans4,ans1};
        String[] opt2 = new String[]{ans3,ans4,ans1,ans2};
        String[] opt3 = new String[]{ans4,ans1,ans2,ans3};

        String[][] options = new String[][]{opt,opt1,opt2,opt3};
        int opti = qus.nextInt(opt.length);

        String[] answers = options[opti];
        int indexa =Arrays.asList(answers).indexOf(ans1);
        Button[] buttons = new Button[]{b1,b2,b3,b4};

        b1.setText(answers[0]);
        b2.setText(answers[1]);
        b3.setText(answers[2]);
        b4.setText(answers[3]);


//        check_Answer(b1,b2,b3,b4,rs2.get(qusn));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            b1.setBackgroundColor(R.color.red);
                check_Answer(b1,b2,b3,b4,rs.get(qusn),buttons,indexa);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b2,b3,b4,b1,rs.get(qusn),buttons,indexa);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b3,b2,b1,b4,rs.get(qusn),buttons,indexa);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b4,b2,b3,b1,rs.get(qusn),buttons,indexa);

            }
        });


    }

    public void check_Answer(Button a, Button b, Button c, Button d, String ans,Button[] but,int index){

        if(a.getText().toString().equals(ans)){
            a.setBackgroundColor(getResources().getColor(R.color.green));
            b.setClickable(false);
            c.setClickable(false);
            d.setClickable(false);

        }
        else if(!a.getText().toString().equals(ans)){
            a.setBackgroundColor(getResources().getColor(R.color.red));
            b.setClickable(false);
            c.setClickable(false);
            d.setClickable(false);
            but[index].setBackgroundColor(getResources().getColor(R.color.green));


        }



    }

}