package com.example.rahul.gridview.model;

import io.realm.RealmObject;

/**
 * Created by IN-RB on 17-04-2017.
 */

public class Dog extends RealmObject {


    private String name;
    private String color;
    private int age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
