package com.example.musiclibrary.services;

import com.example.musiclibrary.Dtos.SongDto;
import com.example.musiclibrary.models.Playlist;
import com.example.musiclibrary.models.PlaylistSong;
import com.example.musiclibrary.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongService playlistSongService;

    @Autowired SongService songService;

    public List<String> getAllPlaylists() {
        return playlistRepository.findAll().stream()
                .map(Playlist::getName)
                .collect(Collectors.toList());
    }

    public String getPlaylistById(Long id) {
        return playlistRepository.findById(id).map(Playlist::getName).orElse(null);
    }

    public String savePlaylist(String name) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        return playlistRepository.save(playlist).getName();
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public String addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));
        SongDto songDto = songService.getSongById(songId);

        PlaylistSong playlistSong = new PlaylistSong();
        playlistSong.setPlaylistId(playlistId);
        playlistSong.setSongId(songId);
        playlistSongService.savePlaylistSong(playlistSong);

        return playlist.getName();
    }

    public void deleteSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));
        playlistSongService.deletePlaylistSong(songId);
    }
}
