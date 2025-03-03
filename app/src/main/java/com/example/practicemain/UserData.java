package com.example.practicemain;

public class UserData {
    private  String name;
    private String email;
    private  int age;
    private float height;

    public UserData(){

    }
    public UserData(String name, String email, int age, float height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }
}
