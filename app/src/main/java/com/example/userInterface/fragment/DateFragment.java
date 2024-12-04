package com.example.userInterface.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.userInterface.DBHelper;
import com.example.userInterface.R;
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
        Cursor cursor = database.rawQuery("select * from " + DBHelper.TABLE_NAME, null);
        Map<LocalDate, List<String>> map = new HashMap<>();
        while (cursor.moveToNext()) {
            long timestamp = cursor.getLong(cursor.getColumnIndex("date"));
            String challengeName = cursor.getString(cursor.getColumnIndex("challengeName"));
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
            /*
             Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
             */
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
    }
}