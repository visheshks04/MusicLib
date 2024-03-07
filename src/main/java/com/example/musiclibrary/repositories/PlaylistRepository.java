package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
