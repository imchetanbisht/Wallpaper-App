package com.example.wallpaper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {
    private List<Wallpaper> wallpaperList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Wallpaper wallpaper);
    }

    public WallpaperAdapter(List<Wallpaper> wallpaperList, Context context, OnItemClickListener onItemClickListener) {
        this.wallpaperList = wallpaperList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        Wallpaper wallpaper = wallpaperList.get(position);
        Glide.with(context).load(wallpaper.getUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(wallpaper));
    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    public static class WallpaperViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
