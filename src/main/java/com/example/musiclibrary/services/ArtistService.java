package com.example.musiclibrary.services;

import com.example.musiclibrary.models.Artist;
import com.example.musiclibrary.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<String> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(Artist::getName)
                .collect(Collectors.toList());
    }

    public String getArtistById(Long id) {
        return artistRepository.findById(id).map(Artist::getName).orElse(null);
    }

    public String saveArtist(String name) {
        Artist artist = new Artist();
        artist.setName(name);
        return artistRepository.save(artist).getName();
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}

