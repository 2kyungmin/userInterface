package com.example.userInterface.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.userInterface.R;

public class ChooseChallengeFragment extends Fragment {
    private static String selectedChallenge;
    private String[] challenges = {"5분 플랭크", "5분 걷기", "5분 밥 먹기", "5분 식사하기", "5분 뛰기"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_choose, container, false);

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

    static class ChallengePagerAdapter extends PagerAdapter {
        private final String[] challenges;

        public ChallengePagerAdapter(String[] challenges) {
            this.challenges = challenges;
        }

        @Override
        public int getCount() {
            return challenges.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.page_challenge, container, false);

            TextView challengeText = itemView.findViewById(R.id.challengeText);
            challengeText.setText(challenges[position]);

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}

