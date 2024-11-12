package com.example.userInterface.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;
import com.example.userInterface.application.LoginApplication;
import com.example.userInterface.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!LoginApplication.checkAuth()){
            binding.logoutTextView.setVisibility(View.VISIBLE);
            binding.mainRecyclerView.setVisibility(View.GONE);
        } else{
            binding.logoutTextView.setVisibility(View.GONE);
            binding.mainRecyclerView.setVisibility(View.VISIBLE);
            makeRecyclerView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(this, AuthActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void makeRecyclerView() {

    }
}