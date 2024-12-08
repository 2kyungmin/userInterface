package com.example.userInterface.fragment;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.activity.ChallengeActivity;
import com.example.userInterface.databinding.FragmentWriteBinding;
import com.example.userInterface.dto.Emoji;
import com.example.userInterface.dto.Review;

import java.util.Date;

public class WriteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private FragmentWriteBinding binding;
    private String challengeName;
    private Date date;
    private Emoji emoji;

    public WriteFragment() {}

    public WriteFragment(String challengeName, Date date) {
        this.challengeName = challengeName;
        this.date = date;
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

        binding.submitButton.setOnClickListener(v -> {
            if (emoji == null) {
                Toast.makeText(getActivity().getBaseContext(),
                        "이모티콘을 선택해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.reviewInput.getText().toString().isEmpty()) {
                Toast.makeText(getActivity().getBaseContext(),
                        "후기를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            Review review = new Review(Application.myUser.getuId(), challengeName,
                    Application.myUser.getName(), emoji.getStr(),
                    binding.reviewInput.getText().toString(), 0, date);
            Log.d("KM", review.toString());
            Application.db.collection("review")
                    .document(review.getuId() + review.getDate()).set(review);

            // 후기를 등록한 후 Challenge Activity로 이동
            Intent intent = new Intent(getActivity().getApplicationContext(), ChallengeActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // 뒤로 가기 버튼 처리
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("경고")
                .setMessage("후기 작성을 종료하시겠습니까?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // ChallengeFragment로 이동
                    ChallengeFragment challengeFragment = new ChallengeFragment(); // ChallengeFragment의 인스턴스 생성
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, challengeFragment); // container는 Fragment를 교체할 ViewGroup의 ID
                    transaction.addToBackStack(null); // 이전 Fragment로 돌아갈 수 있도록 추가
                    transaction.commit();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void changeEmoji(Emoji emoji) {
        this.emoji = emoji;
    }
}
