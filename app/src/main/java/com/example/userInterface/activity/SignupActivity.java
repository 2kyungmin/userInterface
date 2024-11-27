package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.dto.User;
import com.example.userInterface.databinding.ActivitySignupBinding;
import com.google.firebase.firestore.CollectionReference;

public class SignupActivity extends AppCompatActivity {
    private String name = "";
    private int gender = 3; //남자가 0, 여자가 1
    private int age = -1;
    private String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.radioGroupGender.setOnCheckedChangeListener((group, checkedId) -> {
            gender = group.equals(binding.radioMale) ? 0 : 1;
        });

        binding.age.setMinValue(10);
        binding.age.setMaxValue(100);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goals_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerGoals.setAdapter(adapter);

        binding.btnSubmit.setOnClickListener(v -> {
            //이름 확인
            if (binding.edtName.getText() != null) {
                if (binding.edtName.getText().toString().isEmpty())
                    Toast.makeText(getBaseContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                else
                    name = binding.edtName.getText().toString();
            }
            //성별 확인
            if (gender == 3)
                Toast.makeText(getBaseContext(), "성별을 입력해주세요.", Toast.LENGTH_SHORT).show();
            //나이 확인
            age = binding.age.getValue();
            //챌린지 확인
            if(binding.spinnerGoals.getSelectedItem() == null)
                Toast.makeText(getBaseContext(), "챌린지를 선택해주세요.", Toast.LENGTH_SHORT).show();
            else
                category = binding.spinnerGoals.getSelectedItem().toString();

            if (!name.isEmpty() && gender != 3 && age > 0) {
                if(Application.user.getUid() == null){
                    Toast.makeText(getBaseContext(), "회원 정보가 없습니다. 로그인 혹은 회원가입을 해주세요.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    finish();
                }
                else{
                    Application.myUser = new User(Application.user.getUid(), name, gender, age, category);
                    Log.d("KM", Application.myUser.toString());
                    CollectionReference users = Application.db.collection("users");
                    users.document(Application.user.getUid()).set(Application.myUser);
                    startActivity(new Intent(SignupActivity.this, ChallengeActivity.class));
                    finish();
                }
            }
        });
    }
}