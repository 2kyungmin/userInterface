package com.example.userInterface.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.userInterface.R;
import com.example.userInterface.databinding.ActivityChallengeBinding;
import com.example.userInterface.fragment.ChallengeFragment;
import com.example.userInterface.fragment.CommunityFragment;
import com.example.userInterface.fragment.DateFragment;

public class ChallengeActivity extends AppCompatActivity {



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
            if(itemId == R.id.nav_community){
                transferTo(new CommunityFragment());
                return true;
            }
            if(itemId == R.id.nav_my_challenge){
                transferTo(new ChallengeFragment());
                return true;
            }
            if(itemId == R.id.nav_date){
                transferTo(new DateFragment());
                return true;
            }

            return false;
        });


    }
    private void transferTo(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}