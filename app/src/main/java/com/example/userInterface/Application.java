package com.example.userInterface;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDexApplication;

import com.example.userInterface.dto.User;
import com.example.userInterface.fragment.ChallengeChooseFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Application extends MultiDexApplication {
    public static FirebaseAuth auth;
    public static String email;
    public static FirebaseUser user;
    public static FirebaseFirestore db;
    public static User myUser;
    public static SharedPreferences myChallenges;
    public static List<ChallengeChooseFragment> fragments;

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
        db = FirebaseFirestore.getInstance();
        myUser = new User();
        myChallenges = getSharedPreferences("myChallenges",Context.MODE_PRIVATE);
        fragments = new ArrayList<>();
    }

    public static void logout() {
        auth.signOut();
    }
}
