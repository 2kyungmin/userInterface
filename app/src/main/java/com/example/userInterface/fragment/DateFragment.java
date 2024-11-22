package com.example.userInterface.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userInterface.R;
import com.example.userInterface.databinding.ActivityDateBinding;

public class DateFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityDateBinding binding = ActivityDateBinding.inflate(getLayoutInflater());

//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//
//        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
//            String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
//            String review = sharedPreferences.getString(selectedDate, null);
//
//            if (review != null) {
//                binding.reviewTextView.setText(review);
//            } else {
//                binding.reviewTextView.setText("해당 날짜에 성취 기록이 없습니다");
//            }
//        });
    }
}