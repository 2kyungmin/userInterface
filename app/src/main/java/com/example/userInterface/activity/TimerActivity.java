package com.example.userInterface.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;

import java.util.Date;


public class TimerActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startButton, resetButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 300000; // 5초로 설정
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });

        resetButton.setOnClickListener(v -> {
            pauseTimer();
            resetTimer();
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                Intent resultIntent = new Intent();
                String challengeName = getIntent().getStringExtra("challengeName");
                Log.d("KM", "5 Finish: " + challengeName);
                resultIntent.putExtra("challengeName", challengeName);
                setResult(MainActivity.RESULT_OK, resultIntent);
                finish();
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("일시정지");
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isTimerRunning = false;
        startButton.setText("재시작");
    }

    private void resetTimer() {
        timeLeftInMillis = 300000; // 5분으로 초기화
        updateTimerText();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerText.setText(timeFormatted);
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("경고")
                .setMessage("챌린지를 종료하시겠습니까?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    pauseTimer();
                    finish();
                })
                .setNegativeButton("N", (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}