package com.example.userInterface.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.userInterface.Application;
import com.example.userInterface.databinding.FragmentChallengeBinding;
import com.example.userInterface.dto.Category;

import java.util.ArrayList;
import java.util.List;

public class ChallengeFragment extends Fragment {
    private FragmentChallengeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChallengeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TextView 설정
        binding.title.setText(Application.myUser.getName() + "의 챌린지");
        Log.d("KM", Application.myUser.getName() + "의 챌린지: " + Application.myUser.getCategories());
        binding.registerButton.setOnClickListener(v -> {
            // 챌린지 시작 버튼
        });

        // Fragment 추가

        if(Application.fragments.isEmpty()){
            List<String> categories = Application.myUser.getCategories();
            List<String> challenges = new ArrayList<>();
            for (String s : categories) {
                challenges.addAll(Category.getList(s));
            }
            Log.d("KM", challenges.toString());

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

            for (String s : challenges) {
                TestFragment test = TestFragment.newInstance(s);
                Application.fragments.add(test);
                fragmentTransaction.add(binding.fragmentContainer.getId(), test, s);
            }
            fragmentTransaction.commit();
        }

    }
}