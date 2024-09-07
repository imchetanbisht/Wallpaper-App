package com.example.wallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WallpaperAdapter adapter;
    private List<Wallpaper> wallpaperList = new ArrayList<>();
    private final String PEXELS_API_KEY = "pRq5Q9X6bWB9zJtfau08KTpZjzwNpHk8s55yQWB74DUJUGImKQEdzKwL";
    private final int PER_PAGE = 50;  // Number of wallpapers to fetch
    private SearchView searchView;
    private CardView cardNature, cardMountains, cardFlowers, cardCars, cardTrending, cardAnimals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchlayout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));  // Use GridLayoutManager with 2 columns

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        adapter = new WallpaperAdapter(wallpaperList, this, wallpaper -> {
            if (wallpaper != null && wallpaper.getUrl() != null) {
                Intent intent = new Intent(MainActivity.this, WallpaperDetailActivity.class);
                intent.putExtra("IMAGE_URL", wallpaper.getUrl());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Wallpaper URL is null", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        cardNature = findViewById(R.id.card_nature);
        cardMountains = findViewById(R.id.card_mountains);
        cardFlowers = findViewById(R.id.card_flowers);
        cardCars = findViewById(R.id.card_cars);
        cardTrending = findViewById(R.id.card_trending);
        cardAnimals = findViewById(R.id.card_animals);

        cardNature.setOnClickListener(v -> fetchWallpapers("nature"));
        cardMountains.setOnClickListener(v -> fetchWallpapers("mountains"));
        cardFlowers.setOnClickListener(v -> fetchWallpapers("flowers"));
        cardCars.setOnClickListener(v -> fetchWallpapers("cars"));
        cardTrending.setOnClickListener(v -> fetchWallpapers("trending"));
        cardAnimals.setOnClickListener(v -> fetchWallpapers("animals"));

        fetchWallpapers("nature");  // Fetch default wallpapers

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchWallpapers(query);  // Fetch wallpapers based on query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optionally fetch wallpapers as the user types
                return false;
            }
        });
    }

    private void fetchWallpapers(String query) {
        PexelsApiService apiService = RetrofitClient.getRetrofitInstance().create(PexelsApiService.class);
        Call<PexelsResponse> call = apiService.searchWallpapers(query, PEXELS_API_KEY, PER_PAGE);

        call.enqueue(new Callback<PexelsResponse>() {
            @Override
            public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    wallpaperList.clear();  // Clear the previous list
                    List<Photo> photos = response.body().getPhotos();
                    for (Photo photo : photos) {
                        wallpaperList.add(new Wallpaper(photo.getSrc().getMedium()));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch wallpapers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PexelsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
