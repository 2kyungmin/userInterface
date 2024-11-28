package com.example.userInterface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
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

        // Fragment 추가
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        if (Application.fragments.isEmpty()) {
            // category에 해당하는 challenge들 획득
            List<String> categories = Application.myUser.getCategories();
            List<String> challenges = new ArrayList<>();
            for (String s : categories) {
                challenges.addAll(Category.getList(s));
            }
            Log.d("KM", challenges.toString());

            // challenge 이름에 해당하는 Fragment제작
            for (String s : challenges) {
                ChallengeChooseFragment challengeChooseFragment = ChallengeChooseFragment.newInstance(s);

                Application.fragments.add(challengeChooseFragment);
                fragmentTransaction.add(binding.fragmentContainer.getId(), challengeChooseFragment, s);
            }
            fragmentTransaction.commit();
        }
        else{
            Log.d("KM", "here");
            for(ChallengeChooseFragment fragment: Application.fragments){
                Log.d("KM", "Fragment: "+fragment.getChallengeName());
                fragmentTransaction.add(binding.fragmentContainer.getId(), fragment, fragment.getChallengeName());
            }
            fragmentTransaction.commit();
        }

    }
}