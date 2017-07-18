package com.test.shwetha.testspotsoon.model;


/**
 * Created by shwetha on 18/07/17.
 */

public class MyModel {
    int musicImage;
    String heading;
    String subHeading;
    String description;

    public MyModel(int musicImage, String heading, String subHeading, String description) {
        this.musicImage = musicImage;
        this.heading = heading;
        this.subHeading = subHeading;
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMusicImage() {
        return musicImage;
    }

    public void setMusicImage(int musicImage) {
        this.musicImage = musicImage;
    }
}
