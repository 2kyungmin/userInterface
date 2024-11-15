package com.example.userInterface.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.Application;
import com.example.userInterface.data.Category;
import com.example.userInterface.databinding.ActivityChooseBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChooseActivity extends AppCompatActivity {
    private ActivityChooseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Category> categories = new ArrayList<>();

        for(int i=0; i< Category.values().length; i++){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(Category.values()[i].getCategoryName());
            checkBox.setOnCheckedChangeListener((view, isChecked) -> {
                Log.d("KM", view.getText().toString());
                if(isChecked)
                    categories.add(Category.fromString(view.getText().toString()));
                else categories.remove(Category.fromString(view.getText().toString()));
            });
            binding.category.addView(checkBox);
        }

        binding.buttonStart.setOnClickListener(v -> {
            Log.d("KM", Application.myUser.toString());
            Application.myUser.setCategories(categories);
            Application.db.collection("users")
                    .document(Application.user.getUid()).set(Application.myUser);
        });
    }
}
