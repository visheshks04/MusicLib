package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
