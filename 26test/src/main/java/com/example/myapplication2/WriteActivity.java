package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        String selectedChallenge = getIntent().getStringExtra("selectedChallenge");

        TextView titleText = findViewById(R.id.titleText);
        titleText.setText("내가 진행한 챌린지");

        TextView challengeText = findViewById(R.id.challengeText);
        challengeText.setText(selectedChallenge);

        RadioGroup emojiGroup = findViewById(R.id.emojiGroup);
        Button submitButton = findViewById(R.id.submitButton);
        EditText reviewInput = findViewById(R.id.reviewInput);

        submitButton.setOnClickListener(v -> {
            int selectedId = emojiGroup.getCheckedRadioButtonId();
            RadioButton selectedEmoji = findViewById(selectedId);
            String review = reviewInput.getText().toString();

            // 후기 저장 로직 추가 (예: 데이터베이스에 저장)
        });
    }
}
