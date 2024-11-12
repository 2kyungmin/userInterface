package com.example.userInterface.activity;

import static java.util.Objects.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.application.LoginApplication;
import com.example.userInterface.databinding.ActivityEsignBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.Objects;

public class ESignActivity extends AppCompatActivity {
    private ActivityEsignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEsignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();
            if(password.length() < 6){
                Toast.makeText(getBaseContext(), "비밀번호를 6자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                binding.password.getText().clear();
            } else {
                LoginApplication.auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(ESignActivity.this, task -> {
                            if(task.isSuccessful()){
                                LoginApplication.auth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(sendTask -> {
                                            if(sendTask.isSuccessful()){

                                                //나중에 Dialog로 작성
                                                Toast.makeText(getBaseContext(),
                                                        "회원가입에 성공하였습니다. 전송된 메일을 확인해주세요", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ESignActivity.this, SignupActivity.class)
                                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                            } else{
                                                Toast.makeText(getBaseContext(), "메일 전송에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                                recreate();
                                            }
                                        });
                            } else{
                                String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                                String message="";
                                switch (error){
                                    case "ERROR_INVALID_EMAIL":
                                        message = "이메일 형식이 올바르지 않습니다.";
                                        break;
                                    case "ERROR_EMAIL_ALREADY_IN_USE":
                                        message = "이미 사용 중인 이메일입니다.";
                                        break;
                                    default:
                                        message = "회원가입에 실패하였습니다.";
                                        break;
                                }
                                Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                                recreate();
                            }
                        });
            }
        });
    }
}