package com.example.userInterface.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.application.LoginApplication;
import com.example.userInterface.data.User;
import com.example.userInterface.databinding.ActivitySignupBinding;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private String name = "";
    private int gender = 3; //남자가 0, 여자가 1
    private int age = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.radioGroupGender.setOnCheckedChangeListener((group, checkedId) -> {
            gender = group.equals(binding.radioMale) ? 0 : 1;
        });

        binding.age.setMinValue(10);
        binding.age.setMaxValue(100);

        binding.btnSubmit.setOnClickListener(v -> {

            if (binding.edtName.getText() != null) {
                if (binding.edtName.getText().toString().isEmpty())
                    Toast.makeText(getBaseContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                else
                    name = binding.edtName.getText().toString();
            }
            if (gender == 3)
                Toast.makeText(getBaseContext(), "성별을 입력해주세요.", Toast.LENGTH_SHORT).show();

            age = binding.age.getValue();


            if (!name.isEmpty() && gender != 3 && age > 0) {
            /*
            버튼 클릭시 정보 확인하는 Dialog 제작한 후 확인 클릭시 아래 코드 입력
             */

            }

        });

    }
}