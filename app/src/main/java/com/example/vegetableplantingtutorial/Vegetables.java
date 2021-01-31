package com.example.vegetableplantingtutorial;

public class Vegetables {

    private String id;
    private String name;
    private String description;
    private String url;
    private String imageName;
    private String category;

    public Vegetables(String id, String name, String description, String url, String imageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.imageName = imageName;
    }

    public Vegetables(String id, String name, String description, String url, String imageName, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.imageName = imageName;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
