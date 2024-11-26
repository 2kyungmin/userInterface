package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ChallengePagerAdapter extends PagerAdapter {
    private String[] challenges;
    private LayoutInflater inflater;

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
        inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
