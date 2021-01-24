package com.homie.musicplayer.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homie.musicplayer.Adapter.ArtistAdapter;
import com.homie.musicplayer.R;

import static com.homie.musicplayer.MainActivity.artists;

public class ArtistFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArtistAdapter mArtistAdapter;

    public ArtistFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container,false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        if(!(artists.size() < 1)){
            mArtistAdapter = new ArtistAdapter(getContext(),artists );
            mRecyclerView.setAdapter(mArtistAdapter);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext()
                    ,2));
        }

        return view;
    }
}