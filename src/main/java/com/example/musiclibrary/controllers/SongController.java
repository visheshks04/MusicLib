package com.example.musiclibrary.controllers;

import com.example.musiclibrary.Dtos.SongDto;
import com.example.musiclibrary.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("")
    public List<SongDto> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public SongDto getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }

    @PostMapping("")
    public SongDto saveSong(@RequestBody SongDto songDto) {
        return songService.saveSong(songDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
    }
}
