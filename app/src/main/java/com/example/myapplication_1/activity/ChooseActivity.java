package com.example.myapplication_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class ChooseActivity extends AppCompatActivity {

    private EditText editTextName;
    private RadioGroup radioGroupGoals;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        editTextName = findViewById(R.id.editTextName);
        radioGroupGoals = findViewById(R.id.radioGroupGoals);
        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();

                int selectedId = radioGroupGoals.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String goal = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "목표 미선택";

                Toast.makeText(ChooseActivity.this, "이름: " + name + ", 목표: " + goal, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
