package com.example.myapplication_1.activity;

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

import com.example.myapplication_1.R;

public class AfterActivity extends AppCompatActivity {

        private ImageButton settingsButton;
        private TextView titleText;
        private TextView subtitleText;
        private ImageView challengeImage;
        private RadioGroup emotionGroup;
        private EditText inputText;
        private Button registerButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_after); // XML 파일 이름 확인


            settingsButton = findViewById(R.id.settingsButton);
            titleText = findViewById(R.id.titleText);
            subtitleText = findViewById(R.id.subtitleText);
            challengeImage = findViewById(R.id.challengeImage);
            inputText = findViewById(R.id.inputText);
            registerButton = findViewById(R.id.registerButton);


            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(AfterActivity.this, "설정 버튼 클릭됨", Toast.LENGTH_SHORT).show();
                }
            });


            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = inputText.getText().toString();
                    int selectedId = emotionGroup.getCheckedRadioButtonId();
                    RadioButton selectedEmoji = findViewById(selectedId);


                    if (!input.isEmpty() && selectedId != -1) {
                        String emoji = selectedEmoji.getText().toString();
                        Toast.makeText(AfterActivity.this, "등록 완료: " + input + " " + emoji, Toast.LENGTH_SHORT).show();
                        inputText.setText("");
                        emotionGroup.clearCheck();
                    } else {
                        Toast.makeText(AfterActivity.this, "내용과 감정을 입력하세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
