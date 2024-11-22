package com.example.myapplication_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class SignupActivity extends AppCompatActivity {

    private EditText edtName, edtBirthYear, edtBirthMonth, edtBirthDay;
    private RadioGroup radioGroupGender;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtName = findViewById(R.id.edt_name);
        edtBirthYear = findViewById(R.id.edt_birth_year);
        edtBirthMonth = findViewById(R.id.edt_birth_month);
        edtBirthDay = findViewById(R.id.edt_birth_day);
        radioGroupGender = findViewById(R.id.radioGroup_gender);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        String name = edtName.getText().toString().trim();
        String birthYear = edtBirthYear.getText().toString().trim();
        String birthMonth = edtBirthMonth.getText().toString().trim();
        String birthDay = edtBirthDay.getText().toString().trim();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender != null ? selectedGender.getText().toString() : "";

        if (name.isEmpty() || birthYear.isEmpty() || birthMonth.isEmpty() || birthDay.isEmpty() || gender.isEmpty()) {
            Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 사용자 정보를 담은 Intent 생성
        Intent intent = new Intent(SignupActivity.this, ChooseActivity.class);
        intent.putExtra("userName", name); // 사용자 이름을 Intent에 추가
        startActivity(intent);
        finish(); // 현재 Activity 종료
    }
}
