package com.kyung.testapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kyung.testapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    private int currentIndex = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.kyung.testapp.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FrameLayout fragmentContainer = binding.fragmentContainer;

        fragments = new ArrayList<>();
        fragments.add(new Test3Fragment());
        fragments.add(new Test2Fragment());
        fragments.add(new Test1Fragment());
        currentIndex = fragments.size() - 1;
        //container에 fragment를 넣는 코드
        for (Fragment f : fragments) {
            getSupportFragmentManager().beginTransaction()
                    .add(binding.fragmentContainer.getId(), f).commit();
        }

        fragmentContainer.setOnTouchListener(new View.OnTouchListener() {
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
                                || Math.abs(event.getRawX() - initialX) > 100) { // 특정 거리 이상 드래그한 경우
                            // 현재 상단의 프래그먼트를 제거
                            removeTopFragment();
                        }
                        // 프래그먼트를 원래 위치로 되돌림
                        v.animate()
                                .x(vx)
                                .y(vy)
                                .setDuration(300)
                                .start();

                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    // 상단에 있는 프래그먼트를 제거하는 메서드
    private void removeTopFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment topFragment = fragments.get(currentIndex);

        if (topFragment != null) {

            fragmentManager.beginTransaction()
                    .hide(topFragment)
                    .commit();

            currentIndex = (currentIndex - 1 + fragments.size()) % fragments.size();

            if (fragments.get(currentIndex).isHidden())
                fragmentManager.beginTransaction()
                        .show(fragments.get(currentIndex))
                        .commit();
        }
    }
}