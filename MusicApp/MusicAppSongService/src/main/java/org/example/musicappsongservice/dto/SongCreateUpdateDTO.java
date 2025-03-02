package org.example.musicappsongservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongCreateUpdateDTO {
    private String title;
    private int releaseYear;
    private String genre;
    private int views;
    private UUID artistId;
    private String artistStagename;
}
