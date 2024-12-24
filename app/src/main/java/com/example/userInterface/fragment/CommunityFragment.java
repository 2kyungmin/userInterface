package com.example.userInterface.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.firebase.firestore.DocumentReference;

import java.util.List;
import java.util.Map;


public class CommunityFragment extends Fragment {

    private List<Review> reviewList;

    public CommunityFragment(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

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

            holder.binding.likeButton.setOnClickListener(v -> {
                int count = Integer.parseInt(holder.binding.likeCount.getText().toString()) + 1;
                holder.binding.likeCount.setText(String.valueOf(count));
                getThread(count, review).start();
                holder.binding.likeButton.setClickable(false);
            });
        }

        @Override
        public int getItemCount() {
            return reviewList.size();
        }
    }

    private Thread getThread(int count, Review review){
        Thread thread = new Thread(() -> {
            Application.db.collection("review")
                    .document(review.getKey())
                    .update("clickNum", count)
                    .addOnSuccessListener(unused -> {
                        DocumentReference document = Application.db.collection("review")
                                .document(review.getKey());
                        document.get().addOnSuccessListener(documentSnapshot -> {
                            Map<String, Object> data = documentSnapshot.getData();
                            Log.d("KM", data.get("clickNum").toString());
                        });

                    })
                    .addOnFailureListener(e -> {
                        Log.d("KM", "failure");
                        Log.d("KM", e.getLocalizedMessage());
                    });

        });
        return thread;
    }
}

