package com.example.musiclibrary.services;

import com.example.musiclibrary.Dtos.SongDto;
import com.example.musiclibrary.models.Album;
import com.example.musiclibrary.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<String> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(Album::getTitle)
                .collect(Collectors.toList());
    }

    public String getAlbumById(Long id) {
        return albumRepository.findById(id).map(Album::getTitle).orElse(null);
    }

    public String saveAlbum(String title) {
        Album album = new Album();
        album.setTitle(title);
        return albumRepository.save(album).getTitle();
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}

