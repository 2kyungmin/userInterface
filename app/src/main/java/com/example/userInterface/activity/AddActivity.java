package com.example.userInterface.activity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.Application;
import com.example.userInterface.databinding.ActivityAddBinding;
import com.example.userInterface.fragment.ChallengeChooseFragment;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddBinding binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            String newChallenge = binding.inputChallengeName.getText().toString();
            new AlertDialog.Builder(this)
                    .setTitle("챌린지 추가")
                    .setMessage(newChallenge + "를 추가하시겠습니까?")
                    .setPositiveButton("확인", (dialog, which) -> {

                        Application.myChallenges.edit()
                                .putString("challengeName", newChallenge+" 5-GO")
                                .apply();
                        Application.fragments.add(ChallengeChooseFragment.newInstance(newChallenge+" 5-GO"));
                        Intent intent = new Intent(this, ChallengeActivity.class);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    })
                    .setNegativeButton("취소", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
    }
}