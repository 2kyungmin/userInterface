package com.example.userInterface.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.R;
import com.example.userInterface.application.LoginApplication;
import com.example.userInterface.databinding.ActivityAuthBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


public class AuthActivity extends AppCompatActivity {
    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (LoginApplication.checkAuth()) {
            changeVisibility("login");
        } else {
            changeVisibility("logout");
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
                                .addOnCompleteListener(AuthActivity.this, task2 -> {
                                    if (task2.isSuccessful()) {
                                        LoginApplication.email = account.getEmail();
                                        changeVisibility("login");
                                    } else {
                                        changeVisibility("logout");
                                    }
                                });

                    } catch (ApiException e) {
                        LoginApplication.auth.signOut();
                        LoginApplication.email = null;
                        changeVisibility("logout");
                    }
                });

        binding.logoutBtn.setOnClickListener(v -> {
            LoginApplication.auth.signOut();
            LoginApplication.email = null;
            changeVisibility("logout");
        });

        binding.goSignInBtn.setOnClickListener(v -> {
            changeVisibility("signin");
        });

        //구글 로그인
        binding.googleLoginBtn.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            Intent signInIntent = GoogleSignIn.getClient(this, gso).getSignInIntent();
            launcher.launch(signInIntent);
        });

        //이메일, 비밀번호 회원가입
        binding.signBtn.setOnClickListener(v -> {
            String email = binding.authEmailEditView.toString();
            String password = binding.authPasswordEditView.getText().toString();
            LoginApplication.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(AuthActivity.this, task -> {
                        binding.authEmailEditView.getText().clear();
                        binding.authPasswordEditView.getText().clear();
                        if (task.isSuccessful()) {
                            LoginApplication.auth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(sendTask -> {
                                        if (sendTask.isSuccessful()) {
                                            Toast.makeText(getBaseContext(),
                                                    "회원가입에 성공하였습니다. 전송된 메일을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                            changeVisibility("logout");
                                        } else {
                                            Toast.makeText(getBaseContext(), "메일 전송 실패", Toast.LENGTH_SHORT).show();
                                            changeVisibility("logout");
                                        }
                                    });
                        } else {
                            Toast.makeText(getBaseContext(), "회원 가입 실패", Toast.LENGTH_SHORT).show();
                            changeVisibility("logout");
                        }
                    });
        });

        //이메일, 비밀번호 로그인
        binding.loginBtn.setOnClickListener(v -> {
            String email = binding.authEmailEditView.toString();
            String password = binding.authPasswordEditView.getText().toString();
            LoginApplication.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(AuthActivity.this, task -> {
                        binding.authEmailEditView.getText().clear();
                        binding.authPasswordEditView.getText().clear();
                        if (task.isSuccessful()) {
                            if (LoginApplication.checkAuth()) {
                                LoginApplication.email = email;
                                changeVisibility("login");
                            } else {
                                Toast.makeText(getBaseContext(), "전송된 메일로 이메일 인증이 되지 않았습니다", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    protected void changeVisibility(String mode) {
        if (mode.equals("login")) {
            binding.authMainTextView.setText(LoginApplication.email + "님 반갑습니다.");
            binding.logoutBtn.setVisibility(View.VISIBLE);
            binding.goSignInBtn.setVisibility(View.GONE);
            binding.googleLoginBtn.setVisibility(View.GONE);
            binding.authMainTextView.setVisibility(View.GONE);
            binding.authPasswordEditView.setVisibility(View.GONE);
            binding.signBtn.setVisibility(View.GONE);
            binding.logoutBtn.setVisibility(View.GONE);
        } else if (mode.equals("logout")) {
            binding.authMainTextView.setText("로그인 하거나 회원가입 해주세요.");
            binding.logoutBtn.setVisibility(View.GONE);
            binding.goSignInBtn.setVisibility(View.VISIBLE);
            binding.googleLoginBtn.setVisibility(View.VISIBLE);
            binding.authMainTextView.setVisibility(View.VISIBLE);
            binding.authPasswordEditView.setVisibility(View.VISIBLE);
            binding.signBtn.setVisibility(View.GONE);
            binding.logoutBtn.setVisibility(View.VISIBLE);
        } else if (mode.equals("signin")) {
            binding.logoutBtn.setVisibility(View.GONE);
            binding.goSignInBtn.setVisibility(View.GONE);
            binding.googleLoginBtn.setVisibility(View.GONE);
            binding.authMainTextView.setVisibility(View.VISIBLE);
            binding.authPasswordEditView.setVisibility(View.VISIBLE);
            binding.signBtn.setVisibility(View.VISIBLE);
            binding.logoutBtn.setVisibility(View.GONE);
        }
    }
}