package com.example.userInterface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.userInterface.Application;
import com.example.userInterface.activity.AddActivity;
import com.example.userInterface.activity.ChallengeActivity;
import com.example.userInterface.databinding.FragmentChallengeBinding;
import com.example.userInterface.dto.Category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        if (Application.fragments.isEmpty()) {
            // category에 해당하는 challenge들 획득

            List<String> categories = Application.myUser.getCategories();
            Set<String> challenges = new HashSet<>();
            for (String str : categories) {
                challenges.addAll(Category.getList(str));
            }
            Application.myChallenges.edit().putStringSet("challengeName", challenges).apply();

            // challenge 이름에 해당하는 Fragment제작
            challenges = Application.myChallenges.getStringSet("challengeName", null);
            if (challenges.isEmpty()) {
                Toast.makeText(getActivity().getBaseContext(), "챌린지 목록이 없습니다. 추가해주세요",
                        Toast.LENGTH_SHORT).show();
            } else {
                for (String s : challenges) {
                    ChallengeChooseFragment challengeChooseFragment
                            = ChallengeChooseFragment.newInstance(s);
                    Application.fragments.add(challengeChooseFragment);
                    fragmentTransaction.add(binding.container.getId(), challengeChooseFragment, s);
                }
                fragmentTransaction.commit();
            }
        } else {
            for (ChallengeChooseFragment fragment : Application.fragments) {
                fragmentTransaction.add(binding.container.getId(), fragment, fragment.getChallengeName());
            }
            fragmentTransaction.commit();
        }

        binding.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddActivity.class);
            startActivity(intent);
        });
    }
}