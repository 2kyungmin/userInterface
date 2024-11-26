package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ChallengeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            String selectedChallenge = ChallengeFragment.getSelectedChallenge();
            Intent intent = new Intent(ChallengeActivity.this, EndActivity.class);
            intent.putExtra("selectedChallenge", selectedChallenge);
            startActivity(intent);
        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ChallengeFragment())
                .commit();
    }
}