package com.example.userInterface.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;

import java.util.Calendar;

public class AfterActivity extends AppCompatActivity {

    private EditText inputText;
    private Button registerButton;
    private RadioButton happyEmoji, sadEmoji, angryEmoji;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);

        inputText = findViewById(R.id.inputText);
        registerButton = findViewById(R.id.registerButton);
        happyEmoji = findViewById(R.id.happyEmoji);
        sadEmoji = findViewById(R.id.sadEmoji);
        angryEmoji = findViewById(R.id.angryEmoji);

        sharedPreferences = getSharedPreferences("myChallenges", MODE_PRIVATE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();

                String emoji = "";
                if (happyEmoji.isChecked()) {
                    emoji = "😊";
                } else if (sadEmoji.isChecked()) {
                    emoji = "😢";
                } else if (angryEmoji.isChecked()) {
                    emoji = "😠";
                }

                if (input.isEmpty() || emoji.isEmpty()) {
                    Toast.makeText(AfterActivity.this, "내용과 감정을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String achievement = "성취도: " + input + " (" + emoji + ")";
                String currentDate = getCurrentDate();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(currentDate, achievement);
                editor.apply();

                Toast.makeText(AfterActivity.this, "성취도가 등록되었습니다!", Toast.LENGTH_SHORT).show();

                inputText.setText("");
                happyEmoji.setChecked(false);
                sadEmoji.setChecked(false);
                angryEmoji.setChecked(false);
            }
        });
    }

    private String getCurrentDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }
}