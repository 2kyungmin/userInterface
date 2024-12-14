package com.example.userInterface.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
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
import com.example.userInterface.activity.ChallengeActivity;

import java.util.Date;

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
        // history db에 기록
        SQLiteDatabase writableDB = new DBHelper(getActivity()).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN1_1, date.getTime());
        values.put(DBHelper.COLUMN1_2, challengeName);
        Thread thread = new Thread(() -> {
            writableDB.insert(DBHelper.TABLE_NAME1, null, values);
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
