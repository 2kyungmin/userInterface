package com.example.userInterface.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;


public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        // Yes 버튼 클릭 리스너
        findViewById(R.id.yesButton).setOnClickListener(v -> {
            // Yes 버튼 클릭 시 동작
        });

        // No 버튼 클릭 리스너
        findViewById(R.id.noButton).setOnClickListener(v -> {
            // No 버튼 클릭 시 동작
        });
    }
}