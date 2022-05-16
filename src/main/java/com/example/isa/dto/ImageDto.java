package com.example.isa.dto;

import com.example.isa.model.Image;

public class ImageDto {
    public String imgStr;
    public String path;

    public ImageDto(String imgStr, String path) {
        this.imgStr = imgStr;
        this.path = path;
    }

    public ImageDto(){}

    public String getImgStr() {
        return imgStr;
    }

    public String getPath() {
        return path;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
