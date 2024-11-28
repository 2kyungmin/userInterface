package com.example.userInterface.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleObserver;

import com.example.userInterface.R;
import com.example.userInterface.databinding.ActivityChallengeBinding;
import com.example.userInterface.fragment.AfterFragment;
import com.example.userInterface.fragment.ChallengeFragment;
import com.example.userInterface.fragment.CommunityFragment;
import com.example.userInterface.fragment.DateFragment;
import com.example.userInterface.fragment.WriteFragment;
import com.google.android.material.navigation.NavigationBarView;

public class ChallengeActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> timerResultLauncher;
    private boolean isNavigation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChallengeBinding binding = ActivityChallengeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            // Activity가 처음 실행될 때 challenge Fragment 추가
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(binding.container.getId(), new ChallengeFragment());
            transaction.commit();
        }
        binding.bottomNavigation.setSelectedItemId(R.id.nav_my_challenge);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(isNavigation){
                if (itemId == R.id.nav_community) {
                    transferTo(new CommunityFragment(), "community");
                    return true;
                }
                if (itemId == R.id.nav_my_challenge) {
                    transferTo(new ChallengeFragment(), "myChallenge");
                    return true;
                }
                if (itemId == R.id.nav_date) {
                    transferTo(new DateFragment(), "date");
                    return true;
                }
            }
            return false;
        });

        timerResultLauncher
                = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                o -> {
                    if (o.getResultCode() == Activity.RESULT_OK && o.getData() != null) {
                        String finish = o.getData().getStringExtra("challengeName");
                        Log.d("KM", "Restart: " + finish);
                        transferTo(new AfterFragment(finish), "after");
                    }
                });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void openTimer(String challengeName) {
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra("challengeName", challengeName);
        timerResultLauncher.launch(intent);
    }

    public void openWrite(String challengeName) {
        Intent intent = new Intent();
        intent.putExtra("challengeName", challengeName);
        Log.d("KM", "openWrite: " + challengeName);
        transferTo(new WriteFragment(), "write");
    }

    private void transferTo(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);

        if(tag.equals("write"))
            isNavigation = false;

        if (existingFragment == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, existingFragment)
                    .commit();
        }
    }

}