package com.example.userInterface.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.userInterface.R;
import com.example.userInterface.activity.ChallengeActivity;

public class AfterFragment extends Fragment {
    private String challengeName;

    public AfterFragment(String challengeName) {
        this.challengeName = challengeName;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_after, container, false);

        TextView challengeText = view.findViewById(R.id.challengeText);
        challengeText.setText(challengeName);
        showCompletionDialog();
        return view;
    }

    private void showCompletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("챌린지의 후기와, 오늘의 성취도를 기록해보세요!")
                .setPositiveButton("YES", (dialog, which) -> {
                    Log.d("KM", "yes: "+challengeName);
                    if(getActivity() instanceof ChallengeActivity){
                        ((ChallengeActivity) getActivity()).openWrite(challengeName);
                    }
                })
                .setNegativeButton("NO", (dialog, which) -> {
                    // No click시 원래 Main화면으로 이동 되게 설정
                });

        builder.show();
    }
}
