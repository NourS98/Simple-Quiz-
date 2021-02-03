package com.example.assignment5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    int totalQuestionNum= 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);
        TextView scoreTV = findViewById(R.id.score);
        TextView totalTV = findViewById(R.id.total);
        String totalGrade =getString(R.string.totalQuestion,totalQuestionNum);
        int score = getIntent().getIntExtra("score",0);
    //    String resultText = getString(R.string.finalResult, score, 5);
        totalTV.setText(totalGrade);
        scoreTV.setText(getString(R.string.score,score));
      //  total.setText(getString(R.string.totalQuestion,));

    }
}

