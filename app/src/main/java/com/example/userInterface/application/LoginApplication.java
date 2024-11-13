package com.example.userInterface.application;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginApplication extends MultiDexApplication {
    public static FirebaseAuth auth;
    public static String email;
    public static FirebaseUser user;
    public static boolean verified = false;

    public static boolean checkAuth() {
        if (user != null) {
            Log.d("KM", "Application: " + user.getEmail());
            return user.isEmailVerified();
        } else return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }
}
