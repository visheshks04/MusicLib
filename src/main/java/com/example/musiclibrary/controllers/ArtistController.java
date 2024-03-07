package com.example.musiclibrary.controllers;

import com.example.musiclibrary.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<String> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public String getArtistById(@PathVariable Long id) {
        return artistService.getArtistById(id);
    }

    @PostMapping
    public String saveArtist(@RequestBody String name) {
        return artistService.saveArtist(name);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
    }

}
