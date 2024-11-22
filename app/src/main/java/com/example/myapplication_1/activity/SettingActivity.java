package com.example.myapplication_1.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class SettingActivity extends AppCompatActivity {

    private TextView achievementsTextView;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        achievementsTextView = findViewById(R.id.achievementsTextView);
        clearButton = findViewById(R.id.clearButton);

        // SharedPreferences에서 저장된 성취도 가져오기
        loadAchievements();

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAchievements();
            }
        });
    }

    private void loadAchievements() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String achievements = sharedPreferences.getString("achievements", "등록된 성취도가 없습니다.");
        achievementsTextView.setText(achievements);
    }

    private void clearAchievements() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("achievements");
        editor.apply();
        achievementsTextView.setText("등록된 성취도가 없습니다.");
    }
}


