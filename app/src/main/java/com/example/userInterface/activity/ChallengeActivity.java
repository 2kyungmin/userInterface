package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;
import com.example.userInterface.data.Category;


public class ChallengeActivity extends AppCompatActivity {

    private TextView titleText, subtitleText, messageText;
    private ImageView challengeImage;
    private Button yesButton, noButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        // Intent로부터 선택된 목표를 가져옴
        Intent intent = getIntent();
        String goal = intent.getStringExtra("goal");

        // UI 요소 초기화
        titleText = findViewById(R.id.titleText);
        subtitleText = findViewById(R.id.subtitleText);
        messageText = findViewById(R.id.messageText);
        challengeImage = findViewById(R.id.challengeImage);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        settingsButton = findViewById(R.id.settingsButton);

        // 목표에 따라 제목, 부제목 및 이미지를 설정
        setupChallenge(goal);

        // 버튼 클릭 리스너 설정
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TimerActivity로 이동
                Intent timerIntent = new Intent(ChallengeActivity.this, TimerActivity.class);
                startActivity(timerIntent);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 목표를 다시 시도할 수 있도록 안내
                // 관련 로직을 추가할 수 있습니다.
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SettingsActivity로 이동
                Intent settingsIntent = new Intent(ChallengeActivity.this, SettingActivity.class);
                startActivity(settingsIntent);
            }
        });
    }

    private void setupChallenge(Category goal) {
        titleText.setText("CHALLENGE");

        // 목표에 따라 부제목 설정
        subtitleText.setText(goal + " 5-GO");

        // 목표에 따라 이미지를 변경
        switch (goal) {
//            case "운동":
//                challengeImage.setImageResource(R.drawable.exercise_image); // 운동 이미지
//                break;
//            case "건강":
//                challengeImage.setImageResource(R.drawable.health_image); // 건강 이미지
//                break;
//            case "독서":
//                challengeImage.setImageResource(R.drawable.reading_image); // 독서 이미지
//                break;
//            case "영단어":
//                challengeImage.setImageResource(R.drawable.vocabulary_imagee); // 영단어 이미지
//                break;
//            default:
//                challengeImage.setImageResource(R.drawable.default_image); // 기본 이미지
//                break;
        }
    }
}


