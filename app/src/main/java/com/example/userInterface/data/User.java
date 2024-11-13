package com.example.userInterface.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String name;
    private int gender;
    private int age;
    private Set<Category> categories;

    public User(String name, int gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.categories = new HashSet<>();
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void putCategories(Category category) {
        this.categories.add(category);
    }
}
