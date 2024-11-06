package com.example.myapplication_1.test;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication_1.data.Category;
import com.example.myapplication_1.databinding.ExerciseFragmentBinding;

public class Fragment extends androidx.fragment.app.Fragment {

    public Fragment(Category category) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExerciseFragmentBinding binding = ExerciseFragmentBinding.inflate(getLayoutInflater());
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
