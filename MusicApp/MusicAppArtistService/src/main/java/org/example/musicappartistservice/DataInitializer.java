package org.example.musicappartistservice;

import org.example.musicappartistservice.dto.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Configuration
public class DataInitializer {

    private final String songServiceUrl = "http://localhost:8081/api/songs";

    @Bean
    CommandLineRunner init(ArtistService artistService, RestTemplate restTemplate) {
        return args -> {
            Artist liluzivert = Artist.builder()
                    .id(UUID.randomUUID())
                    .name("Symere")
                    .surname("Woods")
                    .age(26)
                    .stagename("Lil Uzi Vert")
                    .popularity(9)
                    .build();

            Artist juicewrld = Artist.builder()
                    .id(UUID.randomUUID())
                    .name("Jarad")
                    .surname("Higgins")
                    .age(21)
                    .stagename("Juice WRLD")
                    .popularity(8)
                    .build();

            Artist playboicarti = Artist.builder()
                    .id(UUID.randomUUID())
                    .name("Jordan")
                    .surname("Carter")
                    .age(29)
                    .stagename("Playboi Carti")
                    .popularity(10)
                    .build();


            artistService.save(liluzivert);
            artistService.save(juicewrld);
            artistService.save(playboicarti);

            postArtistSongs(restTemplate, liluzivert.getId(), "XO Tour Llif3", 2017, 1000, "Hip-Hop");
            postArtistSongs(restTemplate, liluzivert.getId(), "Money Longer", 2016, 900, "Hip-Hop");
            postArtistSongs(restTemplate, liluzivert.getId(), "Do What I Want", 2016, 800, "Hip-Hop");

            postArtistSongs(restTemplate, juicewrld.getId(), "Lucid Dreams", 2018, 1000, "Hip-Hop");
            postArtistSongs(restTemplate, juicewrld.getId(), "All Girls Are The Same", 2018, 900, "Hip-Hop");
            postArtistSongs(restTemplate, juicewrld.getId(), "Robbery", 2019, 750, "Hip-Hop");

            postArtistSongs(restTemplate, playboicarti.getId(), "Magnolia", 2017, 1000, "Hip-Hop");
            postArtistSongs(restTemplate, playboicarti.getId(), "wokeuplikethis*", 2017, 900, "Hip-Hop");
            postArtistSongs(restTemplate, playboicarti.getId(), "Shoota", 2018, 800, "Hip-Hop");
        };
    }

    private void postArtistSongs(RestTemplate restTemplate, UUID artistId, String title, int releaseYear, int views, String genre) {
        SongCreateUpdateDTO songDto = new SongCreateUpdateDTO();
        songDto.setArtistId(artistId);
        songDto.setTitle(title);
        songDto.setReleaseYear(releaseYear);
        songDto.setViews(views);
        songDto.setGenre(genre);

        try {
            restTemplate.postForEntity(songServiceUrl, songDto, Void.class);
            System.out.println("Song posted successfully: " + title);
        } catch (Exception e) {
            System.err.println("Failed to send song: " + e.getMessage());
        }
    }
}
