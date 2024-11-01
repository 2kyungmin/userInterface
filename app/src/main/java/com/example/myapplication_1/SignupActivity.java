package com.example.myapplication_1;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText edtName = findViewById(R.id.edt_name);
        EditText edtBirthday = findViewById(R.id.edt_birth_day);
        Button btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

