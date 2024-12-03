package com.example.userInterface.fragment;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.activity.ChallengeActivity;
import com.example.userInterface.databinding.FragmentWriteBinding;
import com.example.userInterface.dto.Emoji;
import com.example.userInterface.dto.Review;
import com.google.firebase.firestore.DocumentReference;


public class WriteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private FragmentWriteBinding binding;
    private String challengeName;
    private Emoji emoji;

    public WriteFragment() {

    }

    public static WriteFragment newInstance(String param1) {
        WriteFragment fragment = new WriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            challengeName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.challengeText.setText(challengeName + "를 해냈어요!");

        // 라디오 버튼 리스너
        binding.emojiGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.emoji_angry) {
                changeEmoji(Emoji.ANGRY);
            }
            if (checkedId == R.id.emoji_sad) {
                changeEmoji(Emoji.SAD);
            }
            if (checkedId == R.id.emoji_happy) {
                changeEmoji(Emoji.HAPPY);
            }
        });

        // 등록 버튼 리스너
        binding.submitButton.setOnClickListener(v -> {
            if (emoji == null) {
                Toast.makeText(getActivity().getBaseContext(),
                        "이모티콘을 선택해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.reviewInput.getText().toString().isEmpty()){
                Toast.makeText(getActivity().getBaseContext(),
                        "후기를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            Review review = new Review(Application.myUser.getuId(), challengeName,
                    Application.myUser.getName(), emoji.getStr(),
                    binding.reviewInput.getText().toString(), 0);
            Log.d("KM", review.toString());
            Application.db.collection("review")
                    .document(review.getuId()+review.getDate()).set(review);

            Intent intent = new Intent(getActivity().getApplicationContext(), ChallengeActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    private void changeEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

}