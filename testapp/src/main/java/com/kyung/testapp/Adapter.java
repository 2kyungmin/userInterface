package com.kyung.testapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication_1.data.Category;

public class Adapter extends FragmentStateAdapter {

    public Adapter(FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        Category[] categories = Category.values();
        for(int i=0; i < position; i++){
            return new com.example.myapplication_1.testKM.Fragment(categories[i]);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return Category.values().length;
    }
}
