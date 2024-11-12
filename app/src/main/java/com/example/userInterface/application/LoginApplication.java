package com.example.userInterface.application;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginApplication extends MultiDexApplication {
    public static FirebaseAuth auth;
    public static String email;

    public static Boolean checkAuth() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            email = currentUser.getEmail();
            return currentUser.isEmailVerified();
        } else return false;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        auth = FirebaseAuth.getInstance();
    }

}
