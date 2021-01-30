package com.example.vegetableplantingtutorial;

import android.graphics.Bitmap;

public class VegetableModel {

    private int id;
    private String name;
    private String description;
    private String tutorialURI;
    private int category_id;
    private String category_name;
    private Bitmap image;

    public VegetableModel(int id, String name, String description, String tutorialURI, int category_id, String category_name, Bitmap image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tutorialURI = tutorialURI;
        this.category_id = category_id;
        this.category_name = category_name;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTutorialURI() {
        return tutorialURI;
    }

    public void setTutorialURI(String tutorialURI) {
        this.tutorialURI = tutorialURI;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
