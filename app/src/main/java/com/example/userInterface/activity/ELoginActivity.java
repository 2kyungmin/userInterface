package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.userInterface.R;
import com.example.userInterface.application.LoginApplication;
import com.example.userInterface.databinding.ActivityEloginBinding;
import com.google.firebase.auth.FirebaseAuthException;

public class ELoginActivity extends AppCompatActivity {
    private ActivityEloginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEloginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();
            LoginApplication.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ELoginActivity.this, task ->{
                        Toast.makeText(getBaseContext(), email+"\n"+password, Toast.LENGTH_SHORT).show();
                        if(task.isSuccessful()){
                            if(LoginApplication.checkAuth()){
                                LoginApplication.email = email;
                                Intent intent = new Intent(ELoginActivity.this, SignupActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                                Toast.makeText(getBaseContext(), error="11", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                            Toast.makeText(getBaseContext(), error="22", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}