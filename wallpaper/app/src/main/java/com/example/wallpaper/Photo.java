package com.example.wallpaper;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    private int id;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("url")
    private String url;

    @SerializedName("photographer")
    private String photographer;

    @SerializedName("src")
    private Src src;

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getPhotographer() {
        return photographer;
    }

    public Src getSrc() {
        return src;
    }

    public class Src {
        @SerializedName("original")
        private String original;

        @SerializedName("medium")
        private String medium;

        public String getOriginal() {
            return original;
        }

        public String getMedium() {
            return medium;
        }
    }
}
