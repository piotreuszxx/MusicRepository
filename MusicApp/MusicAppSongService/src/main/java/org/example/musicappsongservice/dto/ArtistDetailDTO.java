package org.example.musicappsongservice.dto;

import org.example.musicappsongservice.Artist;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDetailDTO {
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private String stagename;
    private int popularity;

    public ArtistDetailDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.surname = artist.getSurname();
        this.age = artist.getAge();
        this.stagename = artist.getStagename();
        this.popularity = artist.getPopularity();
    }
}
