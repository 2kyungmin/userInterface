package com.example.userInterface.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.databinding.CommunityBinding;
import com.example.userInterface.dto.Emoji;
import com.example.userInterface.dto.Review;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;


public class CommunityFragment extends Fragment {

    private List<Review> reviewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.communityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new Adapter(reviewList));

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR, -24);
        Date dayAgo = calendar.getTime();

        reviewList = new ArrayList<>();

        Application.db.collection("review")
                .whereGreaterThan("date", dayAgo)
                .get()
                .addOnCompleteListener(task -> {
                    QuerySnapshot result = task.getResult();
                    if (result != null) {
                        result.forEach(review -> {
                            reviewList.add(review.toObject(Review.class));
                        });
                    } else {
                        Toast.makeText(getActivity().getBaseContext(),
                                "아직 작성된 후기가 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private CommunityBinding binding;

        public ViewHolder(CommunityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Review> reviewList;

        public Adapter(List<Review> reviewList) {
            this.reviewList = reviewList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CommunityBinding binding = CommunityBinding.inflate(getLayoutInflater());
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Review review = reviewList.get(position);
            holder.binding.nickname.setText(review.getName());

            String[] emojis = getResources().getStringArray(R.array.emoji);
            if (review.getEmoji().equals(Emoji.ANGRY.getStr()))
                holder.binding.emojiCircle.setText(emojis[0]);
            if (review.getEmoji().equals(Emoji.SAD.getStr()))
                holder.binding.emojiCircle.setText(emojis[1]);
            if (review.getEmoji().equals(Emoji.HAPPY.getStr()))
                holder.binding.emojiCircle.setText(emojis[2]);

            holder.binding.reviewText.setText(review.getText());
            holder.binding.likeCount.setText(String.valueOf(review.getClickNum()));
        }

        @Override
        public int getItemCount() {
            return reviewList.size();
        }
    }

    private void changeReview(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}

