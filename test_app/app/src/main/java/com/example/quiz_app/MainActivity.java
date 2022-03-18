package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int ix=0;
    int total_question=0;
    ViewGroup quiz_layer;
    int traniner=0;
    TextView tv,result,tq;
    int question_number=0;
    Button b1,b2,b3,b4,b5,check_ans;
    Button read,privi;
    ArrayList<String> rs,rs2;
    Spinner spinner;
    Switch randm,write_swich;
    EditText w_ans;
    String strs;
    LinearLayout textviews;
    ArrayList<Integer> re_li_item=new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        getSupportActionBar().hide();


// Action Bar hiding
        getSupportActionBar().hide();



        tv=findViewById(R.id.textView2);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        read=findViewById(R.id.read);
        privi=findViewById(R.id.privious);
        result = findViewById(R.id.rw);
        quiz_layer = findViewById(R.id.layer_color);
        spinner=findViewById(R.id.spin);
        randm=findViewById(R.id.change);
        tq=findViewById(R.id.tq);
        textviews = findViewById(R.id.linearLayout8);
        w_ans=findViewById(R.id.writeanswer);
        write_swich=findViewById(R.id.write_swich);
        check_ans=findViewById(R.id.check_ans);

        w_ans.setVisibility(View.GONE);
        check_ans.setVisibility(View.GONE);

// Making list for question And Answer
         rs = new ArrayList<String>();
        rs2 = new ArrayList<String>(rs.size());


        Intent intent=getIntent();
        Bundle bd=intent.getExtras();

        String[] dropdown_index = (String[]) bd.get("sahi_padya");

        ArrayAdapter<CharSequence> odp = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item,dropdown_index);
        odp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(odp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rs.clear();
                rs2.clear();
                question_number=0;

                String [] name_of_string = (String[]) bd.get("strings");
                generate_quiz(rs,rs2,name_of_string[i]);
                total_question=name_of_string[i].length();
                strs=name_of_string[i];



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });



    }



    public void generate_quiz(ArrayList<String> rs, ArrayList<String>rs2, String strs){


        Button[] butto = new Button[] {b1,b2,b3,b4};
        check_ans.setVisibility(View.GONE);
        w_ans.setVisibility(View.GONE);

        for(Button x : butto){
            x.setVisibility(View.VISIBLE);
            x.setBackgroundColor(getResources().getColor(R.color.yellow));
            x.setTextColor(getResources().getColor(R.color.myblack));
            if(x!=b1){
             x.setClickable(true);
            }

        }
                //  Read button to go new Screen to Read
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Read.class);
                intent.putExtra("hari","quiz");
                intent.putExtra("om",strs);
                startActivity(intent);
            }
        });



//Splitting String
        String[] qs = strs.split("\n");


// adding element to list
        rs.clear();
        rs2.clear();
        for(int i=0; i<qs.length; i++){
            rs.add(qs[i].split("---")[0]);
            rs2.add(qs[i].split("---")[1]);
        }


        Random qus = new Random();

        randm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    question_number=qus.nextInt(rs2.size());
                }
            }
        });




//  question
        tv.setText(rs.get(question_number));


        int an2 = qus.nextInt(rs2.size());
        int an3 = qus.nextInt(rs2.size());
        int an4 = qus.nextInt(rs2.size());

        String ans1 = rs2.get(question_number);
        String ans2 = rs2.get(an2);
        String ans3 = rs2.get(an3);
        String ans4 = rs2.get(an4);


        write_swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    write_ans(ans1,rs,rs2);
                }
                else{
                    generate_quiz(rs,rs2,strs);

                }
            }
        });


        // next swich butttons implementation
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tq.setVisibility(View.VISIBLE);

                if(write_swich.isChecked()){
                    write_ans(ans1,rs,rs2);
                }
                else{
                traniner++;
                if(question_number<rs.size()-1){
                    if(randm.isChecked()){

                        question_number=qus.nextInt(rs2.size());
                        generate_quiz(rs,rs2,strs);

                    }
                    else if (traniner>3) {
                        question_number++;
                        traniner=1;
                        generate_quiz(rs, rs2, strs);



                    }
                    else{
                        generate_quiz(rs, rs2, strs);
                        result.setText(String.valueOf(rs.size())+"/"+String.valueOf(question_number));

                    }

                }
                else{
                    question_number=0;
                }
                tq.setText("3/"+String.valueOf(traniner));
//                b5.setVisibility(View.INVISIBLE);
                quiz_layer.setBackgroundColor(getResources().getColor(R.color.white));
//                result.setText("");

            }

            }
        });
        // privi  buttton implementation
        privi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tq.setVisibility(View.GONE);

                if(write_swich.isChecked()){
                    write_ans(ans1,rs,rs2);
                }
                else{

                    if(question_number>0){
                        generate_quiz(rs,rs2,strs);
                        result.setText(String.valueOf(rs.size())+"/"+String.valueOf(question_number));
                        question_number--;
                    }


                    else{
                        question_number=rs.size()-1;
                    }
//                b5.setVisibility(View.INVISIBLE);
                    quiz_layer.setBackgroundColor(getResources().getColor(R.color.white));
//                result.setText("");

                }
            }
        });




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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b1,b2,b3,b4,rs2.get(question_number),buttons,indexa);
//                b5.setVisibility(View.VISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b2,b3,b4,b1, rs2.get(question_number),buttons,indexa);
//                b5.setVisibility(View.VISIBLE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b3,b2,b1,b4,rs2.get(question_number),buttons,indexa);
//                b5.setVisibility(View.VISIBLE);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Answer(b4,b2,b3,b1,rs2.get(question_number),buttons,indexa);
//                b5.setVisibility(View.VISIBLE);
            }
        });


    }





    public void check_Answer(Button a, Button b, Button c, Button d, String ans, Button[] but, int index){


        if(a.getText().toString().equals(ans)){
            a.setBackgroundColor(getResources().getColor(R.color.green));
//            quiz_layers.setBackgroundColor(getResources().getColor(R.color.green));
//            result.setTextColor(getResources().getColor(R.color.black));

//            result.setText("Currect");

            b.setClickable(false);
            c.setClickable(false);
            d.setClickable(false);



        }
        else if(!a.getText().toString().equals(ans)){
            a.setBackgroundColor(getResources().getColor(R.color.red));
//            quiz_layers.setBackgroundColor(getResources().getColor(R.color.red));
//            result.setTextColor(getResources().getColor(R.color.black));
//            result.setText("Wrong");
            b.setClickable(false);
            c.setClickable(false);
            d.setClickable(false);
            but[index].setBackgroundColor(getResources().getColor(R.color.green));


        }

    }
    public void write_ans(String answer,ArrayList<String> qus, ArrayList<String> ans){
        View[] togone = new View[]{b1,b2,b3,b4,tq};
        for(View v : togone) {
            v.setVisibility(View.GONE);
        }
        w_ans.setVisibility(View.VISIBLE);
        check_ans.setVisibility(View.VISIBLE);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_qustion_index = qus.indexOf(answer);
                w_ans.setText("");
                if(question_number<qus.size()-1){
                    question_number++;
                }
                else{
                    question_number=0;
                }
                tv.setText(qus.get(question_number));
                result.setText(String.valueOf(rs.size())+"/"+String.valueOf(question_number));


            }
        });

        privi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                w_ans.setText("");
                if(question_number>0){
                    question_number--;
                }
                else{
                    question_number=qus.size()-1;
                }
                tv.setText(qus.get(question_number));
                result.setText(String.valueOf(rs.size())+"/"+String.valueOf(question_number));


            }
        });
        check_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_ans = w_ans.getText().toString();
                if(input_ans.startsWith(ans.get(question_number).substring(0,1))){
                    if(!input_ans.equals(ans.get(question_number))){
                        w_ans.setText(ans.get(question_number));
                    }
                    else{

                    result.setText("Currect");
                    w_ans.setText("");
                    if(question_number<qus.size()-1){
                        question_number++;
                    }
                    else{
                        question_number=0;
                    }
                    tv.setText(qus.get(question_number));
                    result.setText(String.valueOf(rs.size())+"/"+String.valueOf(question_number));

                }
                }

                else{
                    Toast.makeText(MainActivity.this, "wrong Answer\n"+ans.get(question_number), Toast.LENGTH_SHORT).show();
                }

                if(input_ans.isEmpty()){
                    Toast.makeText(MainActivity.this, "Write Answer\n"+ans.get(question_number), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}