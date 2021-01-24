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
import com.homie.musicplayer.Activity.AlbumDetails;
import com.homie.musicplayer.Activity.ArtistDetails;
import com.homie.musicplayer.Model.MusicFiles;
import com.homie.musicplayer.R;

import java.util.ArrayList;

public class ArtistAdapter  extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<MusicFiles> artistFiles;
    View view;

    public ArtistAdapter(Context context, ArrayList<MusicFiles> artistFiles) {
        mContext = context;
        this.artistFiles = artistFiles;
    }

    @NonNull
    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.artist_item, parent, false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ViewHolder holder, final int position) {
        holder.artist_name.setText(artistFiles.get(position).getArtist());
        byte[] image = getAlbumArt(artistFiles.get(position).getPath());
        if(image != null){
            Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(holder.artist_image);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.author_image);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ArtistDetails.class);
                intent.putExtra("artistName", artistFiles.get(position).getArtist());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView artist_image;
        TextView artist_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artist_image = itemView.findViewById(R.id.artist_image);
            artist_name = itemView.findViewById(R.id.artist_name);
        }
    }
    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
