package com.example.musiclibrary.services;

import com.example.musiclibrary.models.PlaylistSong;
import com.example.musiclibrary.repositories.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistSongService {

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    public PlaylistSong savePlaylistSong(PlaylistSong playlistSong) {
        return playlistSongRepository.save(playlistSong);
    }

    public void deletePlaylistSong(Long id) {
        playlistSongRepository.deleteById(id);
    }
}
