package com.example.myapplication_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class ChooseActivity extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerGoals;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        editTextName = findViewById(R.id.editTextName);
        spinnerGoals = findViewById(R.id.spinnerGoals);
        buttonStart = findViewById(R.id.buttonStart);


        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        if (userName != null) {
            editTextName.setText(userName);
        }


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goals_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoals.setAdapter(adapter);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChallenge();
            }
        });
    }

    private void startChallenge() {
        String selectedGoal = spinnerGoals.getSelectedItem().toString();


        if (selectedGoal.equals("")) {
            Toast.makeText(this, "목표를 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(ChooseActivity.this, ChallengeActivity.class);
        intent.putExtra("goal", selectedGoal);
        startActivity(intent);
        finish();
    }
}
