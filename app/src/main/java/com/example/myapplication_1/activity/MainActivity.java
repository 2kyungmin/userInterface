package com.example.myapplication_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignup = findViewById(R.id.btn_signup);
        Button btnLogin = findViewById(R.id.btn_login);
        //일단 테스트용으로 choose화면 버튼으로 바꾸겠음
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                startActivity(intent);
            }
        });
        //테스트용으로 challenge 화면 버튼임
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
                startActivity(intent);
            }
        });
    }
}

