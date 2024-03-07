package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
