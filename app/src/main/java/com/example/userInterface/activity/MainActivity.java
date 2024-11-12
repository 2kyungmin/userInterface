package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;
import com.example.userInterface.application.LoginApplication;
import com.example.userInterface.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 이미 로그인이 되어 있는 상태라면 자동으로 Activity 전환
        if(LoginApplication.checkAuth()){
            binding.googleSign.setVisibility(View.INVISIBLE);
            binding.emailSign.setVisibility(View.INVISIBLE);
            binding.btnLogin.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
                startActivity(intent);
            }, 2000);
            finish();
        }

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                o -> {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        AuthCredential credential = GoogleAuthProvider
                                .getCredential(account.getIdToken(), null);
                        LoginApplication.auth.signInWithCredential(credential)
                                .addOnCompleteListener(MainActivity.this, task2 -> {
                                    if (task2.isSuccessful()) {
                                        LoginApplication.email = account.getEmail();
                                        startActivity(new Intent(MainActivity.this, SignupActivity.class));
                                        finish();
                                    } else {
                                        //회원가입 실패 함수
                                        Toast.makeText(getBaseContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                        recreate();
                                    }
                                });

                    } catch (ApiException e) {
                        LoginApplication.auth.signOut();
                        LoginApplication.email = null;
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        recreate();
                    }
                });


        binding.googleSign.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            Intent signInIntent = GoogleSignIn.getClient(this, gso).getSignInIntent();
            launcher.launch(signInIntent);
        });

        binding.emailSign.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ESignActivity.class));
        });

        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ELoginActivity.class));
        });
    }
}