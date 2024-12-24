package com.example.userInterface.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
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

import com.example.userInterface.Application;
import com.example.userInterface.DBHelper;
import com.example.userInterface.R;
import com.example.userInterface.activity.ChallengeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;

public class AfterFragment extends Fragment {
    private String challengeName;
    private Date date;

    public AfterFragment(String challengeName, Date date) {
        this.challengeName = challengeName;
        this.date = date;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_after, container, false);

        TextView challengeText = view.findViewById(R.id.titleText);
        challengeText.setText(challengeName);
        showCompletionDialog();

            // history count db에 기록
        ContentValues values = new ContentValues();
        ContentValues count = new ContentValues();
        values.put(DBHelper.COLUMN1_1, date.getTime());
        values.put(DBHelper.COLUMN1_2, challengeName);

        Thread thread = new Thread(() -> {
            SQLiteDatabase database = new DBHelper(getActivity()).getWritableDatabase();
            Cursor cursor = database.rawQuery("select " + DBHelper.COLUMN2_2 + " from "
                    + DBHelper.TABLE_NAME2 + " where "
                    + DBHelper.COLUMN2_1 + "= '" + challengeName + "'", null);
            if (cursor.moveToFirst()) {
                int num = cursor.getInt(0) + 1;
                Log.d("KM", "not empty " + challengeName + num);
                count.put(DBHelper.COLUMN2_2, num);
                database.update(DBHelper.TABLE_NAME2, count,
                        DBHelper.COLUMN2_1 + "= '" + challengeName + "'", null);
            } else {
                Log.d("KM", "empty " + challengeName);
                count.put(DBHelper.COLUMN2_1, challengeName);
                count.put(DBHelper.COLUMN2_2, 1);
                database.insert(DBHelper.TABLE_NAME2, null, count);
            }
            cursor.close();
            database.insert(DBHelper.TABLE_NAME1, null, values);
        });
        thread.start();
        return view;
    }

    private void showCompletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("챌린지의 후기와, 오늘의 성취도를 기록해보세요!")
                .setPositiveButton("YES", (dialog, which) -> {
                    Log.d("KM", "yes: " + challengeName);
                    if (getActivity() instanceof ChallengeActivity) {
                        ((ChallengeActivity) getActivity()).openWrite(challengeName, date);
                    }
                })
                .setNegativeButton("NO", (dialog, which) -> {
                    Intent intent = new Intent(getActivity(), ChallengeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                });

        builder.show();
    }
}
