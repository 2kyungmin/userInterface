package com.example.myapplication_1.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText;
    private ImageView clockImage;
    private Button startButton;
    private Button resetButton;

    private CountDownTimer timer;
    private long remainingTime = 300000; // 5분 (300000 밀리초)
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer); // XML 레이아웃 사용

        timerText = findViewById(R.id.timerText);
        clockImage = findViewById(R.id.clockImage);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });
    }

    private void startTimer() {
        isTimerRunning = true;
        timer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                long secondsRemaining = millisUntilFinished / 1000;
                timerText.setText(String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60));
            }

            @Override
            public void onFinish() {
                timerText.setText("00:00");
                isTimerRunning = false; // 타이머 종료
            }
        }.start();
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            timer.cancel();
            isTimerRunning = false; // 타이머가 멈춤
        }
    }

    @Override
    public void onBackPressed() {
        if (isTimerRunning) {
            showExitConfirmationDialog();
        } else {
            super.onBackPressed(); // 타이머가 실행 중이지 않으면 기본 동작 수행
        }
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("경고")
                .setMessage("챌린지를 종료하시겠습니까?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        pauseTimer(); // 타이머 멈춤
                        TimerActivity.super.onBackPressed(); // 기본 뒤로가기 동작 수행
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // 대화상자 닫기
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}


