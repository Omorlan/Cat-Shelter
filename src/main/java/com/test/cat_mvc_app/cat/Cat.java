package com.test.cat_mvc_app.cat;

import org.springframework.stereotype.Component;

@Component
public class Cat {
    private int catId;
    private int catAge;
    private String catBreed;
    private String catDescription;
    private String catName;

    public Cat(int cat_id, String cat_name, int cat_age, String cat_breed, String cat_desc) {
        this.catName = cat_name;
        this.catAge = cat_age;
        this.catBreed = cat_breed;
        this.catDescription = cat_desc;
        this.catId = cat_id;
    }

    public Cat() {

    }

    public Cat(String catName, String catBreed, int catAge, String catDescription) {
        this.catName = catName;
        this.catAge = catAge;
        this.catBreed = catBreed;
        this.catDescription = catDescription;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(String catBreed) {
        this.catBreed = catBreed;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public int getCatAge() {
        return catAge;
    }

    public void setCatAge(int catAge) {
        this.catAge = catAge;
    }
}
