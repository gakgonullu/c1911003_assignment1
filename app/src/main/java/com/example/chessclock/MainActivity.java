package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView countdownText;
    TextView countdownText2;
    Button countdownButton;
    Button countdownButton2;
    Button countdownButton3;

    CountDownTimer countDownTimer;
    CountDownTimer countDownTimer2;
    long timeLeftInMilliseconds=600000;
    long timeLeftInMilliseconds2=600000;
    boolean timerRunning;
    boolean timerRunning2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownText = findViewById(R.id.countdown_text);
        countdownText2 = findViewById(R.id.countdown_text2);
        countdownButton = findViewById(R.id.countdown_button); //change
        countdownButton2 = findViewById(R.id.pause); //pause
        countdownButton3 = findViewById(R.id.reset);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });


        countdownButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allStop();
            }
        });

        countdownButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    void reset(){
        stopTimer();
        stopTimer2();
        timeLeftInMilliseconds=600000;
        timeLeftInMilliseconds2=600000;
        updateTimer();
        updateTimer2();
    }

    void allStop() { //pause
        stopTimer();
        stopTimer2();
    }
    void startStop(){
        if(timerRunning) {
            stopTimer();
            updateTimer2();
            startTimer2();
        }
        else if(timerRunning2){
            stopTimer2();
            updateTimer();
            startTimer();
        }
        else {
            updateTimer();
            startTimer();
        }
    }

    void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {

            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds=l;
                updateTimer();
            }
            @Override
            public void onFinish() {
                timerRunning=false;
            }
        }.start();
        timerRunning=true;
    }
    void startTimer2() {
        countDownTimer2 = new CountDownTimer(timeLeftInMilliseconds2, 1000) {

            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds2=l;
                updateTimer2();
            }
            @Override
            public void onFinish() {
                timerRunning2=false;
            }
        }.start();

        timerRunning2=true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning=false;
    }
    void stopTimer2(){
        countDownTimer2.cancel();
        timerRunning2=false;
    }
    void updateTimer(){
        int minutes=(int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = " "+ minutes;
        timeLeftText += ":";
        if(seconds<10) timeLeftText += "0";
        timeLeftText+= seconds;
        countdownText.setText(timeLeftText);
    }
    void updateTimer2(){
        int minutes=(int) timeLeftInMilliseconds2 / 60000;
        int seconds = (int) timeLeftInMilliseconds2 % 60000 / 1000;

        String timeLeftText;
        timeLeftText = " "+ minutes;
        timeLeftText += ":";
        if(seconds<10) timeLeftText += "0";
        timeLeftText+= seconds;
        countdownText2.setText(timeLeftText);
    }
}