package com.example.rahul.gridview.model;

import io.realm.RealmObject;

/**
 * Created by IN-RB on 17-04-2017.
 */

public class Book extends RealmObject {

    private String title;
    private String description;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
