package com.example.b_dinh.sqlite_import_export_kml_intent;

/**
 * Created by b_dinh on 2015/09/04.
 */
public class GPSCameraApp {
    public String title;
    public String image;
    public String place;
    public String comment;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle(){
        return this.title;
    }

    public String getImage(){
        return this.image;
    }

    public String getPlace(){
        return this.place;
    }

    public String getComment(){
        return this.comment;
    }
}
