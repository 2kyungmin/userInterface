package com.example.userInterface.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.databinding.ActivityDateBinding;


public class DateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDateBinding binding = ActivityDateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            String review = sharedPreferences.getString(selectedDate, null);

            if (review != null) {
                binding.reviewTextView.setText(review);
            } else {
                binding.reviewTextView.setText("해당 날짜에 성취 기록이 없습니다");
            }
        });
    }
}