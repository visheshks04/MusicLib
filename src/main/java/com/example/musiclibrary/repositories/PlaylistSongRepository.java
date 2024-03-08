package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.Playlist;
import com.example.musiclibrary.models.PlaylistSong;
import com.example.musiclibrary.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    @Query("SELECT ps FROM PlaylistSong ps WHERE ps.playlist=:playlist")
    List<PlaylistSong> findByPlaylist(Playlist playlist);
}
