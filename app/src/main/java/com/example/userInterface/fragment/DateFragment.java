package com.example.userInterface.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userInterface.DBHelper;
import com.example.userInterface.databinding.DateBinding;
import com.example.userInterface.databinding.FragmentDateBinding;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateFragment extends Fragment {
    FragmentDateBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SQLiteDatabase database = new DBHelper(getActivity()).getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + DBHelper.TABLE_NAME1, null);
        Map<LocalDate, List<String>> map = new HashMap<>();
        while (cursor.moveToNext()) {
            long timestamp = cursor.getLong(0);
            String challengeName = cursor.getString(1);
            Date date = new Date(timestamp);
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            Log.d("KM", challengeName + '-' + localDate.toString());

            if (map.containsKey(localDate)) {
                List<String> list = map.get(localDate);
                list.add(challengeName);
                map.put(localDate, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(challengeName);
                map.put(localDate, list);
            }
        }

        binding.calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
            List<String> challengeNames = map.get(selectedDate);
            Log.d("KM", selectedDate.toString());

            if (challengeNames != null) {
                binding.reviewTextView.setText(challengeNames.get(0));
            } else {
                binding.reviewTextView.setText("");
            }
        });

        LocalDate today = LocalDate.now();
        List<String> challengeNames = map.get(today);
        if (challengeNames != null) {
            binding.reviewTextView.setText(challengeNames.get(0));
        } else {
            binding.reviewTextView.setText("");
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private DateBinding binding;

        public ViewHolder(DateBinding binding) {
            super(binding.count);
            this.binding = binding;
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{
        private Map<String, Integer> map;

        public Adapter(Map<String, Integer> map) {
            this.map = map;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            DateBinding binding = DateBinding.inflate(getLayoutInflater());
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//            SQLiteDatabase database = new DBHelper(getActivity()).getReadableDatabase();
//            Cursor cursor = database.rawQuery("select * from " + DBHelper.TABLE_NAME2, null);
//            Map<String, Integer> map = new HashMap<>();
//            while (cursor.moveToNext()) {
//                String challengeName = cursor.getString(0);
//                int count = cursor.getInt(1);
//                map.put(challengeName, count);
//            }

        }

        @Override
        public int getItemCount() {
            return map.size();
        }
    }
}