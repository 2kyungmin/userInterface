package com.example.userInterface.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String uId;
    private String name;
    private int gender;
    private int age;
    private List<String> categories;

    public User(String uId, String name, int gender, int age, String category) {
        this.uId = uId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        categories = new ArrayList<>();
        this.categories.add(category);
    }
    public void putCategories(String category) {
        this.categories.add(category);
    }

    @Override
    public String toString() {
        return "User{" +
                "uId='" + uId + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", categories=" + categories +
                '}';
    }

    /*
        Getter, Setter
    */
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
