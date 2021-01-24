package com.homie.musicplayer.Activity;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.homie.musicplayer.Adapter.AlbumDetailsAdapter;
import com.homie.musicplayer.Adapter.ArtistDetailsAdapter;
import com.homie.musicplayer.Model.MusicFiles;
import com.homie.musicplayer.R;

import java.util.ArrayList;

import static com.homie.musicplayer.MainActivity.musicFiles;

public class ArtistDetails extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ImageView artistPhoto;
    String artistName;
    ArrayList<MusicFiles> artistSongs = new ArrayList<>();
    ArtistDetailsAdapter mArtistDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);
        mRecyclerView = findViewById(R.id.artistRecyclerView);
        artistPhoto = findViewById(R.id.artistDetailImage);
        artistName = getIntent().getStringExtra("artistName");
        int j = 0;
        for (int i = 0; i < musicFiles.size(); i++){
            if (artistName.equals(musicFiles.get(i).getArtist())){
                artistSongs.add(j++, musicFiles.get(i));
            }
        }
        byte[] image = getAlbumArt(artistSongs.get(0).getPath());
        if (image != null){
            Glide.with(this)
                    .load(image)
                    .into(artistPhoto);
        } else {
            Glide.with(this)
                    .load(R.drawable.author_image)
                    .into(artistPhoto);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(artistSongs.size() < 1)){
            mArtistDetailsAdapter = new ArtistDetailsAdapter(this, artistSongs);
            mRecyclerView.setAdapter(mArtistDetailsAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                    RecyclerView.VERTICAL, false));
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
