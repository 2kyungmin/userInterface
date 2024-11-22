package com.example.userInterface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.example.userInterface.activity.ChallengeActivity;
import com.example.userInterface.activity.CommunityActivity;
import com.example.userInterface.activity.DateActivity;
import com.example.userInterface.activity.MainActivity;
import com.example.userInterface.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

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

    public static void changeActivity(Activity activity, View view) {
        BottomNavigationView navigation = view.findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                if(item.getItemId() == R.id.nav_community){
                    intent = new Intent(activity, CommunityActivity.class);
                    activity.startActivity(intent);
                    return true;
                }
                if(item.getItemId() == R.id.nav_my_challenge){
                    intent = new Intent(activity, ChallengeActivity.class);
                    activity.startActivity(intent);
                    return true;
                }
                if(item.getItemId() == R.id.nav_calendar){
                    intent = new Intent(activity, DateActivity.class);
                    activity.startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
    }

    public static void logout() {
        auth.signOut();
    }
}
