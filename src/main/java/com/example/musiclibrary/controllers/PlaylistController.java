package com.example.musiclibrary.controllers;

import com.example.musiclibrary.models.Playlist;
import com.example.musiclibrary.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public List<String> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public String getPlaylistById(@PathVariable Long id) {
        return playlistService.getPlaylistById(id);
    }

    @PostMapping
    public String savePlaylist(@RequestBody String name) {
        return playlistService.savePlaylist(name);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
    }

    @PostMapping("/{playlistId}/addSong/{songId}")
    public String addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        return playlistService.addSongToPlaylist(playlistId, songId);
    }

    @DeleteMapping("/{playlistId}/deleteSong/{songId}")
    public void deleteSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        playlistService.deleteSongFromPlaylist(playlistId, songId);
    }
}

