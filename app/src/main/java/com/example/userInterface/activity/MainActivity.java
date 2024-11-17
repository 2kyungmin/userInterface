package com.example.userInterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.userInterface.Application;
import com.example.userInterface.R;
import com.example.userInterface.data.User;
import com.example.userInterface.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 이미 로그인이 되어 있는 상태라면 자동으로 Activity 전환
        if (Application.checkAuth() && Application.user.getUid() != null) {
            Log.d("KM", "already sign "+Application.user.getUid());
            binding.googleSign.setVisibility(View.INVISIBLE);
            binding.googleLogin.setVisibility(View.INVISIBLE);
            DocumentReference document = Application.db.collection("users").document(Application.user.getUid());
            document.get()
                    .addOnSuccessListener(task -> {
                        Application.myUser = task.toObject(User.class);
                        Log.d("KM", "success to get user" + Application.myUser.toString());
                    })
                    .addOnFailureListener(task -> {
                        Log.d("KM", "fail to get user: " + task.getMessage());
                    });
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                startActivity(new Intent(MainActivity.this, ChooseActivity.class));
                finish();
            }, 1000);
        }

        binding.googleLogout.setOnClickListener(v -> {
            Application.logout();
            recreate();
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                //signInIntent 의 result 처리
                o -> {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        Application.auth.fetchSignInMethodsForEmail(account.getEmail())
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        signUpWithGoogleCredential(account);
                                    } else {
                                        Toast.makeText(getBaseContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                        recreate();
                                    }
                                });
                    } catch (ApiException e) {
                        Application.auth.signOut();
                        Application.email = null;
                        recreate();
                    }
                });

        binding.googleSign.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            isSign = true;
            Intent signInIntent = GoogleSignIn.getClient(this, gso).getSignInIntent();
            launcher.launch(signInIntent);
        });

        binding.googleLogin.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            isSign = false;
            Intent signInIntent = GoogleSignIn.getClient(this, gso).getSignInIntent();
            launcher.launch(signInIntent);
        });
    }

    private void signUpWithGoogleCredential(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        Application.auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (isSign && task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, SignupActivity.class));
                        finish();
                    } else if (!isSign && task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, ChallengeActivity.class));
                        finish();
                    } else if (isSign && !task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        recreate();
                    } else if (!isSign && !task.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                });
    }
}