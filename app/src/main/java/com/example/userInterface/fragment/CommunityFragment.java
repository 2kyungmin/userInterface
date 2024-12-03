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

import com.example.userInterface.R;
import com.example.userInterface.databinding.CommunityBinding;

import java.util.ArrayList;
import java.util.List;


public class CommunityFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if(recyclerView == null)
            Log.d("KM", "recyclerView is null");
        /*
        TODO 커뮤니티 리스트 초기화
         */
        List<String> list = new ArrayList<>();
        for (int i=0; i<20; i++)
            list.add(i+"번째 항목");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new Adapter(list));
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        private CommunityBinding binding;
        public ViewHolder(CommunityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{

        private List<String> list;
        private Adapter(List<String> list){
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CommunityBinding binding = CommunityBinding.inflate(getLayoutInflater());
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String text = list.get(position);
            holder.binding.nickname.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

