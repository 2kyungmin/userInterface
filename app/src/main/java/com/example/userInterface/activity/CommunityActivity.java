package com.example.userInterface.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.userInterface.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {

    private EditText reviewInput;
    private Button submitButton;
    private TextView reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        reviewInput = findViewById(R.id.reviewInput);
        submitButton = findViewById(R.id.submitButton);
        reviewList = findViewById(R.id.reviewList);

        // 후기 목록 불러오기
        loadReviews();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = reviewInput.getText().toString().trim();
                if (!review.isEmpty()) {
                    saveReview(review);
                    reviewInput.setText(""); // 입력 필드 초기화
                    loadReviews(); // 후기를 다시 불러오기
                } else {
                    Toast.makeText(CommunityActivity.this, "후기를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveReview(String review) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        List<String> reviews = getReviewsList();
        reviews.add(review);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("reviews", String.join(";", reviews)); // 세미콜론으로 구분하여 저장
        editor.apply();
        Toast.makeText(this, "후기가 등록되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private List<String> getReviewsList() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedReviews = sharedPreferences.getString("reviews", "");
        String[] reviewsArray = savedReviews.split(";");
        List<String> reviews = new ArrayList<>();
        for (String review : reviewsArray) {
            if (!review.isEmpty()) {
                reviews.add(review);
            }
        }
        return reviews;
    }

    private void loadReviews() {
        List<String> reviews = getReviewsList();
        StringBuilder reviewsText = new StringBuilder();
        for (String review : reviews) {
            reviewsText.append(review).append("\n\n"); // 각 후기를 줄 바꿈으로 구분
        }
        reviewList.setText(reviewsText.toString());
    }
}
