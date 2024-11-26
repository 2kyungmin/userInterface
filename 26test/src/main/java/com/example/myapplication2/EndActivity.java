package com.example.myapplication2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String selectedChallenge = getIntent().getStringExtra("selectedChallenge");

        TextView titleText = findViewById(R.id.title);
        titleText.setText("선택하신 챌린지");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new EndFragment(selectedChallenge))
                .commit();

        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);

        yesButton.setOnClickListener(v -> {
            // 챌린지 시작 로직 추가
            // 예: 새로운 Activity로 이동
//            Intent intent = new Intent(EndActivity.this, AfterActivity.class);
//            startActivity(intent);
        });

        noButton.setOnClickListener(v -> {
//            Intent intent = new Intent(EndActivity.this, WriteActivity.class);
//            startActivity(intent);
            finish(); // 현재 Activity 종료
        });
    }
}

