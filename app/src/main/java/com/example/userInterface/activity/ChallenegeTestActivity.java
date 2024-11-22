package com.example.userInterface.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.userInterface.R;
import com.example.userInterface.databinding.ActivityChallenegeTestBinding;
import com.example.userInterface.fragment.ChallengeFragment;
import com.example.userInterface.fragment.CommunityFragment;
import com.example.userInterface.fragment.DateFragment;

public class ChallenegeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChallenegeTestBinding binding = ActivityChallenegeTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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