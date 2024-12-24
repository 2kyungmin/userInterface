package com.example.userInterface.fragment;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userInterface.DBHelper;
import com.example.userInterface.R;
import com.example.userInterface.activity.FixActivity;
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

        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        cursor.close();
        Adapter adapter = new Adapter(map, LocalDate.now());
        recyclerView.setAdapter(adapter);

        binding.calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
            Log.d("KM", selectedDate.toString());
            adapter.updateData(selectedDate);
        });
        recyclerView.setAdapter(new Adapter(map, LocalDate.now()));

        binding.edit.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FixActivity.class);
            startActivity(intent);
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private DateBinding binding;

        public ViewHolder(DateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private Map<LocalDate, List<String>> map;
        private LocalDate selectedDate;

        public Adapter(Map<LocalDate, List<String>> map, LocalDate selectedDate) {
            this.map = map;
            this.selectedDate = selectedDate;
        }

        public void updateData(LocalDate newDate) {
            this.selectedDate = newDate;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            DateBinding binding = DateBinding.inflate(getLayoutInflater());
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            List<String> names = map.get(selectedDate);
            String challengeName = names.get(position);

            Log.d("KM", "here: "+names+" "+selectedDate.toString());
            SQLiteDatabase database = new DBHelper(getActivity()).getReadableDatabase();
            Cursor cursor = database
                    .rawQuery("select "+DBHelper.COLUMN2_2+" from " + DBHelper.TABLE_NAME2
                            +" where "+DBHelper.COLUMN1_2+"=\""+challengeName+"\"", null);

            while (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                holder.binding.challengeName.setText(challengeName);
                holder.binding.count.setText(String.valueOf(count) + "회 성공");
            }
        }

        @Override
        public int getItemCount() {
            List<String> names = map.get(selectedDate);
            return names == null ? 0 : names.size();
        }
    }
}