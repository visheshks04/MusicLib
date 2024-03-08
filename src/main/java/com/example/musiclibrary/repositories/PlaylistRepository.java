package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.Playlist;
import com.example.musiclibrary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query("SELECT p from Playlist p WHERE p.user=:user")
    List<Playlist> findPlaylistsByUserId(User user);
}
