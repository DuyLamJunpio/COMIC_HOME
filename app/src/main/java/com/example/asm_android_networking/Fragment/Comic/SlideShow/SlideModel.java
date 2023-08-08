package com.example.asm_android_networking.Fragment.Comic.SlideShow;

import java.io.Serializable;

public class SlideModel implements Serializable {
    private String resourceID;
    private String idComic;

    public SlideModel(String resourceID, String idComic) {
        this.resourceID = resourceID;
        this.idComic = idComic;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }
}
