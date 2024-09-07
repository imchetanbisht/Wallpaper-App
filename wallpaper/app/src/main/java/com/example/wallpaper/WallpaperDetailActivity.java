package com.example.wallpaper;


import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;

public class WallpaperDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button setWallpaperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_detail);

        imageView = findViewById(R.id.detail_image_view);
        setWallpaperButton = findViewById(R.id.set_wallpaper_button);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("IMAGE_URL");

        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(imageView);
        } else {
            Toast.makeText(this, "Image URL is null", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity if URL is null
        }

        setWallpaperButton.setOnClickListener(v -> setWallpaper(imageUrl));
    }

    private void setWallpaper(String imageUrl) {
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        try {
                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(WallpaperDetailActivity.this);
                            wallpaperManager.setBitmap(resource);
                            Toast.makeText(WallpaperDetailActivity.this, "Wallpaper Set", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Toast.makeText(WallpaperDetailActivity.this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }
}
