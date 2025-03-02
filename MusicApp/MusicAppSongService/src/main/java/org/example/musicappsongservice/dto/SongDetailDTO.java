package org.example.musicappsongservice.dto;

import org.example.musicappsongservice.Song;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDetailDTO {
    private UUID id;
    private String title;
    private int releaseYear;
    private String genre;
    private int views;
    private UUID artistId;

    public SongDetailDTO(Song song) {
        this.id = song.getId();
        this.title = song.getTitle();
        this.releaseYear = song.getReleaseYear();
        this.genre = song.getGenre();
        this.views = song.getViews();
        this.artistId = song.getArtistId();
    }
}
