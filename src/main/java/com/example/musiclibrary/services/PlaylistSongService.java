package com.example.musiclibrary.services;

import com.example.musiclibrary.models.Playlist;
import com.example.musiclibrary.models.PlaylistSong;
import com.example.musiclibrary.models.Song;
import com.example.musiclibrary.repositories.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PlaylistSong> getSongsByPlaylist(Playlist playlist) {
        List<PlaylistSong> songs = playlistSongRepository.findByPlaylist(playlist);
        return songs;
    }
}
