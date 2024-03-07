package com.example.musiclibrary.Dtos;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private String title;
    private String genre;
    private Long albumId;
    private Long artistId;
}
