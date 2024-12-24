package com.example.userInterface.activity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userInterface.Application;
import com.example.userInterface.databinding.ActivityFixBinding;
import com.example.userInterface.databinding.FixBinding;
import com.example.userInterface.fragment.ChallengeChooseFragment;


import java.util.List;
import java.util.Set;

public class FixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFixBinding binding = ActivityFixBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // RecyclerView 설정
        binding.activityList.setLayoutManager(new LinearLayoutManager(this));

        Set<String> list = Application.myChallenges.getStringSet("challengeName", null);
        // 어댑터 설정
        Adapter adapter = new Adapter(list);
        binding.activityList.setAdapter(adapter);
    }

    // RecyclerView.Adapter 클래스 정의
    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private Set<String> challengeList;

        public Adapter(Set<String> challengeList) {
            this.challengeList = challengeList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FixBinding binding = FixBinding.inflate(getLayoutInflater());
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            String name = challengeList.toArray()[position].toString();
            holder.binding.challengeName.setText(name);

            //삭제 버튼
            holder.binding.button.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(FixActivity.this);
                builder.setMessage(name+"을 삭제하시겠습니까?")
                        .setPositiveButton("네", (dialog, which) -> {
                            Set<String> challengeName
                                    = Application.myChallenges.getStringSet("challengeName", null);
                            challengeName.remove(name);
                            Log.d("KM", "here:"+challengeName.remove(name));
                            Application.myChallenges.edit()
                                    .putStringSet("challengeName", challengeName).apply();
                            Intent intent = new Intent(FixActivity.this, ChallengeActivity.class);
                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                            Application.fragments.remove(ChallengeChooseFragment.newInstance(name));
                            startActivity(intent);
                        })
                        .setNegativeButton("아니오", (dialog, which) -> {
                            dialog.dismiss();
                        }).show();
            });
        }

        @Override
        public int getItemCount() {
            return challengeList.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private FixBinding binding;

        public ViewHolder(FixBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}