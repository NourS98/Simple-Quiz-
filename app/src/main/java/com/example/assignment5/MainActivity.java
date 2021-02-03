package com.example.assignment5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int Q_number = 1;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private TextView Question_content;
    private TextView Question_number;
    private TextView total;
    private Button confirmBtn;
    private Boolean backGroundColor;
    private Boolean isSelected;

    private String[] q1;
    private String[] q2;
    private String[] q3;
    private String[] q4;
    private String[] q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        Question_content = findViewById(R.id.Question_content);
        Question_number = findViewById(R.id.Question_number);
        confirmBtn = findViewById(R.id.confirm_btn);
        total = findViewById(R.id.total);
        backGroundColor=false;
        isSelected=false;
        q1 = getResources().getStringArray(R.array.Question_1);
        q2 = getResources().getStringArray(R.array.Question_2);
        q3 = getResources().getStringArray(R.array.Question_3);
        q4 = getResources().getStringArray(R.array.Question_4);
        q5 = getResources().getStringArray(R.array.Question_5);

        total.setText(getString(R.string.totalQuestion, 5));

        displayQuestion(Q_number);

    }

    private void displayQuestion(int QuetionNumber) {
        switch (QuetionNumber) {
            case 1:
                Question_content.setText(q1[0]);
                radioButton1.setText(q1[1]);
                radioButton2.setText(q1[2]);
                radioButton3.setText(q1[3]);
                Question_number.setText(R.string.Q1_5);
                break;

            case 2:
                Question_content.setText(q2[0]);
                radioButton1.setText(q2[1]);
                radioButton2.setText(q2[2]);
                radioButton3.setText(q2[3]);
                Question_number.setText(R.string.Q2_5);
                break;

            case 3:
                Question_content.setText(q3[0]);
                radioButton1.setText(q3[1]);
                radioButton2.setText(q3[2]);
                radioButton3.setText(q3[3]);
                Question_number.setText(R.string.Q3_5);
                break;

            case 4:
                Question_content.setText(q4[0]);
                radioButton1.setText(q4[1]);
                radioButton2.setText(q4[2]);
                radioButton3.setText(q4[3]);
                Question_number.setText(R.string.Q4_5);
                break;

            case 5:
                Question_content.setText(q5[0]);
                radioButton1.setText(q5[1]);
                radioButton2.setText(q5[2]);
                radioButton3.setText(q4[3]);
                Question_number.setText(R.string.Q5_5);
                break;
        }
    }


    public void confirm_click_handler(View view) {

        if (confirmBtn.getText().toString().equals(getResources().getString(R.string.Confirm))) {
            switch (Q_number) {
                case 1:
                case 3:
                case 5:
                    if (radioGroup.getCheckedRadioButtonId() == radioButton2.getId()) {
                        radioButton2.setBackground(
                                ResourcesCompat.getDrawable(getResources()
                                        , R.drawable.correct_ans
                                        , null));
                        backGroundColor=true;
                        isSelected=true;
                        score++;
                    } else {
                        findViewById(radioGroup.getCheckedRadioButtonId())
                                .setBackground(ResourcesCompat.getDrawable(getResources()
                                        , R.drawable.wrong_ans,
                                        null));
                        isSelected=true;
                        backGroundColor=false;

                    }
                    break;
                case 2:
                case 4:
                    if (radioGroup.getCheckedRadioButtonId() == radioButton3.getId()) {
                        radioButton3.setBackground(ResourcesCompat.getDrawable(
                                getResources()
                                , R.drawable.correct_ans,
                                null));
                        backGroundColor=true;
                        isSelected=true;
                        score++;
                    } else {
                        findViewById(radioGroup.getCheckedRadioButtonId())
                                .setBackground(ResourcesCompat.getDrawable(getResources(),
                                        R.drawable.wrong_ans,
                                        null));
                        isSelected=true;
                        backGroundColor=false;
                    }
                    break;
            }


            if (Q_number != 5) {
                confirmBtn.setText(getResources().getString(R.string.Next));
            } else {
                confirmBtn.setText(getResources().getString(R.string.finish));
            }

        } else {
            isSelected=false;
            backGroundColor=false;
            switch (Q_number) {
                case 1:
                case 2:
                case 3:
                case 4:
                    displayQuestion(Q_number + 1);
                    Q_number++;
                    //   radioButton1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    radioButton1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.transpert_bg, null));
                    radioButton2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.transpert_bg, null));
                    radioButton3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.transpert_bg, null));
                    radioGroup.clearCheck();
                    confirmBtn.setText(getResources().getString(R.string.Confirm));
                    break;

                case 5:
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                    break;
            }

        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Quection_Number", Q_number);
        outState.putInt("Total_Score", score);
        outState.putBoolean("isSelected",isSelected);
        outState.putBoolean("backColor",backGroundColor);
        outState.putString("Button_Text", confirmBtn.getText().toString());
        // outState.putB

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Q_number = savedInstanceState.getInt("Quection_Number");
        score = savedInstanceState.getInt("Total_Score");
        confirmBtn.setText(savedInstanceState.getString("Button_Text"));
        displayQuestion(Q_number);
        if(savedInstanceState.getBoolean("isSelected") ){
            if(savedInstanceState.getBoolean("backColor")){
               getRadioButton(radioGroup.getCheckedRadioButtonId()).setBackground(ResourcesCompat.getDrawable(
                       getResources()
                       , R.drawable.correct_ans,
                       null));
            }else{
                getRadioButton(radioGroup.getCheckedRadioButtonId()).setBackground(ResourcesCompat.getDrawable(
                        getResources()
                        , R.drawable.wrong_ans,
                        null));
            }

        }
    }
    private RadioButton getRadioButton(int id){
       if(radioButton1.getId() == id){
           return radioButton1;
       }else if(radioButton2.getId() ==id){
          return radioButton2 ;
       }  else{
           return radioButton3;}
    }
}