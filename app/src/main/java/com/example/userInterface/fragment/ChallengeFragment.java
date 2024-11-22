package com.example.userInterface.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.databinding.ActivityChallengeBinding;

import java.util.List;

public class ChallengeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_challenge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityChallengeBinding binding = ActivityChallengeBinding.inflate(getLayoutInflater());
        List<String> category = Application.myUser.getCategories();
        Log.d("KM", category.toString());
    }
}