package com.example.userInterface.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;
import com.example.userInterface.data.Category;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Spinner spinnerGoals = findViewById(R.id.spinnerGoals);

        List<String> goalList = new ArrayList<>();
        for (Category category : Category.values()) {
            goalList.add(category.getCategoryName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                goalList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoals.setAdapter(adapter);


        spinnerGoals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGoal = parent.getItemAtPosition(position).toString();
                Toast.makeText(ChooseActivity.this, "선택된 목표: " + selectedGoal, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
