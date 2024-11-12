package com.example.userInterface.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}