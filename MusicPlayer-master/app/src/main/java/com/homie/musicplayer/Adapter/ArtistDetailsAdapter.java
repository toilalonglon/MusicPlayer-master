package com.homie.musicplayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.homie.musicplayer.Activity.PlayerActivity;
import com.homie.musicplayer.Model.MusicFiles;
import com.homie.musicplayer.R;

import java.util.ArrayList;

public class ArtistDetailsAdapter extends RecyclerView.Adapter<ArtistDetailsAdapter.ViewHolder>{
    private Context mContext;
    public static ArrayList<MusicFiles> musicArtistFiles;
    View view;
    public ArtistDetailsAdapter(Context context, ArrayList<MusicFiles> musicArtistFiles) {
        mContext = context;
        this.musicArtistFiles = musicArtistFiles;
    }

    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    @NonNull
    @Override
    public ArtistDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.music_item, parent, false);
        return new ArtistDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistDetailsAdapter.ViewHolder holder, final int position) {
        holder.song_name.setText(musicArtistFiles.get(position).getTitle());
        byte[] image = getAlbumArt(musicArtistFiles.get(position).getPath());
        if (image != null){
            Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(holder.song_image);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.no_music_tianyi)
                    .into(holder.song_image);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("sender", "artistDetails");
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicArtistFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView song_image;
        TextView song_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_image = itemView.findViewById(R.id.music_img);
            song_name = itemView.findViewById(R.id.music_file_name);
        }
    }
}
