package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AfterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);

        String selectedChallenge = getIntent().getStringExtra("selectedChallenge");

        TextView titleText = findViewById(R.id.title);
        titleText.setText("방금 종료된 챌린지");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AfterFragment(selectedChallenge))
                .commit();

        showCompletionDialog();
    }

    private void showCompletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("챌린지의 후기와, 오늘의 성취도를 기록해보세요!");

        builder.setPositiveButton("YES", (dialog, which) -> {
            Intent intent = new Intent(AfterActivity.this, WriteActivity.class);
            intent.putExtra("selectedChallenge", getIntent().getStringExtra("selectedChallenge"));
            startActivity(intent);
        });

        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
