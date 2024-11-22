package com.example.userInterface.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.userInterface.R;

public class DateActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView reviewTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        calendarView = findViewById(R.id.calendarView);
        reviewTextView = findViewById(R.id.reviewTextView);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            String review = sharedPreferences.getString(selectedDate, null);

            if (review != null) {
                reviewTextView.setText(review);
            } else {
                reviewTextView.setText("해당 날짜에 성취 기록이 없습니다");
            }
        });
    }
}