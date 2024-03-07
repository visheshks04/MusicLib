package com.example.musiclibrary.services;

import com.example.musiclibrary.Dtos.SongDto;
import com.example.musiclibrary.models.Song;
import com.example.musiclibrary.repositories.AlbumRepository;
import com.example.musiclibrary.repositories.ArtistRepository;
import com.example.musiclibrary.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;


    public List<SongDto> getAllSongs() {
        return songRepository.findAll().stream()
                .map(song -> modelMapper.map(song, SongDto.class))
                .collect(Collectors.toList());
    }

    public SongDto getSongById(Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        Song song = optionalSong
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
        return convertToDto(song);
    }

    public SongDto saveSong(SongDto songDto) {
        Song song = convertToEntity(songDto);
        return convertToDto(songRepository.save(song));
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    private SongDto convertToDto(Song song) {
        return modelMapper.map(song, SongDto.class);
    }

    private Song convertToEntity(SongDto songDto) {
        Song song = new Song();
        song.setTitle(songDto.getTitle());
        song.setGenre(songDto.getGenre());
        song.setArtist(this.artistRepository.findById(songDto.getArtistId()).get());
        song.setAlbum(this.albumRepository.findById(songDto.getAlbumId()).get());
        return song;
    }

}
