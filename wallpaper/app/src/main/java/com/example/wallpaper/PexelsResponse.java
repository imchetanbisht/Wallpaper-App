package com.example.wallpaper;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PexelsResponse {
    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("page")
    private int page;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("photos")
    private List<Photo> photos;

    public int getTotalResults() {
        return totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}

