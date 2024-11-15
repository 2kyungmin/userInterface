package com.example.userInterface.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;

public class AfterActivity extends AppCompatActivity {

        private ImageButton settingsButton;
        private TextView titleText;
        private TextView subtitleText;
        private ImageView challengeImage;
        private RadioGroup emotionGroup;
        private EditText inputText;
        private Button registerButton;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_after); // XML 파일 이름 확인


            settingsButton = findViewById(R.id.settingsButton);
            titleText = findViewById(R.id.titleText);
            subtitleText = findViewById(R.id.subtitleText);
            challengeImage = findViewById(R.id.challengeImage);
            emotionGroup = findViewById(R.id.emotionGroup);
            inputText = findViewById(R.id.inputText);
            registerButton = findViewById(R.id.registerButton);

            // 설정 버튼 클릭 리스너
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 설정 버튼 클릭 시 동작
                    Toast.makeText(AfterActivity.this, "설정 버튼 클릭됨", Toast.LENGTH_SHORT).show();
                }
            });

            // 등록하기 버튼 클릭 리스너
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = inputText.getText().toString();
                    int selectedId = emotionGroup.getCheckedRadioButtonId();
                    RadioButton selectedEmoji = findViewById(selectedId);

                    // 입력된 내용을 처리하는 로직
                    if (!input.isEmpty() && selectedId != -1) {
                        String emoji = selectedEmoji.getText().toString();
                        Toast.makeText(AfterActivity.this, "등록 완료: " + input + " " + emoji, Toast.LENGTH_SHORT).show();
                        inputText.setText(""); // 입력창 초기화
                        emotionGroup.clearCheck(); // 이모티콘 선택 초기화
                    } else {
                        Toast.makeText(AfterActivity.this, "내용과 감정을 입력하세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
