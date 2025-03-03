package com.example.practicemain;

public class EmployeeData {

    String name;
    String email;
//    float salary;

    EmployeeData() {

    }

    public EmployeeData(String name, String email) {
        this.name = name;
        this.email = email;
//        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public float getSalary() {
//        return salary;
//    }
}
