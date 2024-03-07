package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
}
