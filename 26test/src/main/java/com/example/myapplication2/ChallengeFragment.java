package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ChallengeFragment extends Fragment {
    private static String selectedChallenge;
    private String[] challenges = {"5분 플랭크", "5분 걷기", "5분 밥 먹기", "5분 식사하기", "5분 뛰기"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        ChallengePagerAdapter adapter = new ChallengePagerAdapter(challenges);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                selectedChallenge = challenges[position];
            }
        });

        return view;
    }

    public static String getSelectedChallenge() {
        return selectedChallenge;
    }
}
