package com.example.userInterface;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.example.userInterface.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Application extends MultiDexApplication {
    public static FirebaseAuth auth;
    public static String email;
    public static FirebaseUser user;
    public static FirebaseFirestore db;
    public static User myUser;

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
    }

    public static void logout(){
        auth.signOut();
    }
}
