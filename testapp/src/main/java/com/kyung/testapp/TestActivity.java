package com.kyung.testapp;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication_1.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}

