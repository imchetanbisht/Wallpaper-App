package com.example.wallpaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PexelsApiService {
    @GET("search")
    Call<PexelsResponse> searchWallpapers(
            @Query("query") String query,
            @Header("Authorization") String apiKey,
            @Query("per_page") int perPage
    );
}
