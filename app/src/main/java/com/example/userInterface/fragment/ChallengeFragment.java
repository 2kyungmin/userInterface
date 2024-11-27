package com.example.userInterface.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.databinding.ActivityChallengeBinding;
import com.example.userInterface.databinding.FragmentChallengeBinding;

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


        binding.title.setText(Application.myUser.getName() + "의 챌린지");
        Log.d("KM", Application.myUser.getName() + "의 챌린지: " + Application.myUser.getCategories());
        binding.registerButton.setOnClickListener(v -> {
            // 챌린지 시작 버튼
        });

        FrameLayout container = binding.fragmentContainer;
        List<Fragment> fragments = new ArrayList<>();

        String[] challenges = {"5분 플랭크", "5분 걷기", "5분 밥 먹기", "5분 식사하기", "5분 뛰기"};
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (String s : challenges) {
            TestFragment test = TestFragment.newInstance(s);
            fragmentTransaction.add(binding.fragmentContainer.getId(), test);
        }
        fragmentTransaction.commit();

        List<String> categories = Application.myUser.getCategories();
    }
}