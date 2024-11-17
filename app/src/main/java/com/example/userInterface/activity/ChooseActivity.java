package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.Application;
import com.example.userInterface.data.Category;
import com.example.userInterface.databinding.ActivityChooseBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChooseActivity extends AppCompatActivity {
    private ActivityChooseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> categories = new ArrayList<>();

        for (int i = 0; i < Category.values().length; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(Category.values()[i].getCategoryName());
            checkBox.setOnCheckedChangeListener((view, isChecked) -> {
                if (isChecked) {
                    Log.d("KM", view.getText().toString()+" is checked");
                    categories.add(view.getText().toString());
                } else {
                    Log.d("KM", view.getText().toString()+" is not checked");
                    categories.remove(view.getText().toString());
                }
            });
            binding.category.addView(checkBox);
        }

        binding.buttonStart.setOnClickListener(v -> {
            Application.myUser.setCategories(categories);

            Application.db.collection("users")
                    .document(Application.user.getUid())
                    .update("categories", categories)
                    .addOnSuccessListener(task -> {
                        Log.d("KM", "Success to update category"+Application.myUser.toString());
                    })
                    .addOnFailureListener(task -> {
                        Log.d("KM", "Fail to update category"+task.getMessage());
                    });

            startActivity(new Intent(ChooseActivity.this, ChallengeActivity.class));
            finish();
        });
    }
}
