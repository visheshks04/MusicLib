package com.example.musiclibrary.services;

import com.example.musiclibrary.Dtos.SongDto;
import com.example.musiclibrary.models.Playlist;
import com.example.musiclibrary.models.PlaylistSong;
import com.example.musiclibrary.models.Song;
import com.example.musiclibrary.models.User;
import com.example.musiclibrary.repositories.PlaylistRepository;
import com.example.musiclibrary.repositories.SongRepository;
import com.example.musiclibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongService playlistSongService;

    @Autowired
    SongService songService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    public List<String> getAllPlaylists() {
        return playlistRepository.findAll().stream()
                .map(Playlist::getName)
                .collect(Collectors.toList());
    }

    public String getPlaylistById(Long id) {
        return playlistRepository.findById(id).map(Playlist::getName).orElse(null);
    }

    public String savePlaylist(String name, Long userId) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUser(userRepository.findById(userId).get());
        return playlistRepository.save(playlist).getName();
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public String addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));
        SongDto songDto = songService.getSongById(songId);

        PlaylistSong playlistSong = new PlaylistSong();
        playlistSong.setPlaylist(playlist);
        playlistSong.setSong(songRepository.findById(songId).get());
        playlistSongService.savePlaylistSong(playlistSong);

        return playlist.getName();
    }

    public void deleteSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));
        playlistSongService.deletePlaylistSong(songId);
    }

    public List<String> getPlaylistsByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user;
        if (optionalUser.isPresent()) user = optionalUser.get();
        else return new ArrayList<>();
        return playlistRepository.findPlaylistsByUserId(user).stream()
                .map(Playlist::getName)
                .collect(Collectors.toList());
    }

    public List<PlaylistSong> getPlaylistSongsByPlaylistId(Long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        Playlist playlist;
        if (optionalPlaylist.isPresent()) playlist = optionalPlaylist.get();
        else return new ArrayList<>();

        return playlistSongService.getSongsByPlaylist(playlist);
    }
}
