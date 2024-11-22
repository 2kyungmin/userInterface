package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;

import java.util.ArrayList;

class CommunityActivity extends AppCompatActivity {

    private ListView communityListView;
    private ArrayList<String> achievementList;
    private ArrayAdapter<String> achievementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);


        communityListView = findViewById(R.id.communityListView);
        achievementList = new ArrayList<>();
        achievementAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, achievementList);
        communityListView.setAdapter(achievementAdapter);


        Intent intent = getIntent();
        ArrayList<String> receivedAchievements = intent.getStringArrayListExtra("achievements");

        if (receivedAchievements != null && !receivedAchievements.isEmpty()) {
            achievementList.addAll(receivedAchievements);
            achievementAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "성취도가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}