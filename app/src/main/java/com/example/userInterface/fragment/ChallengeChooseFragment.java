package com.example.userInterface.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.activity.ChallengeActivity;
import com.example.userInterface.activity.TimerActivity;

public class ChallengeChooseFragment extends Fragment {

    private static final String ARG_PARAM1 = "challengeName";
    private String challengeName;
    private static int count;

    public ChallengeChooseFragment() {

    }

    public static ChallengeChooseFragment newInstance(String param1) {
        ChallengeChooseFragment fragment = new ChallengeChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            challengeName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_choose, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text = view.findViewById(R.id.challengeChoose);

        Log.d("KM", "Test: " + challengeName);
        text.setText(challengeName);

        view.setOnTouchListener(new View.OnTouchListener() {
            private float dx, dy, vx, vy;
            private float initialX, initialY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //v.get은 view의 위치, event.getRaw는 터치 위치
                    case MotionEvent.ACTION_DOWN:
                        vx = v.getX();
                        vy = v.getY();
                        initialX = event.getRawX();
                        initialY = event.getRawY();
                        dx = vx - initialX;
                        dy = vy - initialY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.animate()
                                .x(event.getRawX() + dx)
                                .y(event.getRawY() + dy)
                                .setDuration(0)
                                .start();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(event.getRawY() - initialY) > 300
                                || Math.abs(event.getRawX() - initialX) > 300) { // 특정 거리 이상 드래그한 경우
                            // 현재 상단의 프래그먼트를 제거
                            removeTopFragment();
                            v.animate()
                                    .x(vx)
                                    .y(vy)
                                    .setDuration(300)
                                    .start();
                            return true;
                        }
                        else if(Math.abs(event.getRawY() - initialY) > 30
                                || Math.abs(event.getRawX() - initialX) > 30){
                            // 프래그먼트를 원래 위치로 되돌림
                            v.animate()
                                    .x(vx)
                                    .y(vy)
                                    .setDuration(300)
                                    .start();
                            return true;
                        }
                        break;
                }
                return false;
            }
        });

        view.setOnClickListener(v -> {
            if (getActivity() instanceof ChallengeActivity) {
                ((ChallengeActivity) getActivity()).openTimer(challengeName);
            }
        });
    }

    private void removeTopFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();

        fragmentManager.beginTransaction().hide(this).commit();
        Log.d("KM", "Choose: "+this.getTag());
        count++;

        if (count % Application.fragments.size() == 0) {
            for (Fragment f : Application.fragments) {
                fragmentManager.beginTransaction().show(f).commit();
            }
        }
    }

    public String getChallengeName() {
        return challengeName;
    }
}