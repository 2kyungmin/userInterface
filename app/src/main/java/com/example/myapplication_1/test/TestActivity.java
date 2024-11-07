package com.example.myapplication_1.test;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication_1.R;
import com.example.myapplication_1.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    private void bottomNavigationSelect() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            changeFragment(item);
            return true;
        });
    }

    void changeFragment(MenuItem item){
        switch (item.getItemId()){
//            case R.id.community
        }
    }
}

