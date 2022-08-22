package com.example.rahul.gridview.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by IN-RB on 20-04-2017.
 */

public class Person extends RealmObject {

    public RealmList<Dog> dogs;

    private String name;
    private String dept;
    private String email;

    public RealmList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
