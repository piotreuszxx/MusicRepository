package org.example.musicappsongservice;

import org.example.musicappsongservice.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    private final RestTemplate restTemplate;
    private final String ArtistServiceURL = "http://localhost:8082/api/artists/";

    @Autowired
    public SongController(SongService songService, RestTemplate restTemplate) {
        this.songService = songService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<SongCollectionDTO>> getAllSongs() {
        List<SongCollectionDTO> songs = songService.findAll().stream()
                .map(song -> new SongCollectionDTO(song.getId(), song.getTitle()))
                .collect(Collectors.toList());

        if (songs.isEmpty()) {
            // artysta bez piosenek
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDetailDTO> getSongById(@PathVariable UUID id) {
        return songService.findById(id)
                .map(song -> ResponseEntity.ok(new SongDetailDTO(song)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /*
    @PostMapping
    public ResponseEntity<SongDetailDTO> createSong(@RequestBody SongCreateUpdateDTO songDTO) {
        return artistService.findByStagename(songDTO.getArtist().getStagename())
                .map(artist -> {
                    Song newSong = Song.builder()
                            .id(UUID.randomUUID())
                            .title(songDTO.getTitle())
                            .artist(artist)
                            .releaseYear(songDTO.getReleaseYear())
                            .genre(songDTO.getGenre())
                            .views(songDTO.getViews())
                            .build();
                    Song savedSong = songService.save(newSong);
                    return ResponseEntity.status(HttpStatus.CREATED).body(new SongDetailDTO(savedSong));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    */

    @PostMapping
    public ResponseEntity<?> createSong(@RequestBody SongCreateUpdateDTO dto) {
        System.out.println("dto.getArtistId() = " + dto.getArtistId());
        Artist existingArtist = fetchArtistById(dto.getArtistId());
        if (existingArtist == null) {
            return ResponseEntity.badRequest().body("Artist not found.");
        }

        Song newSong = Song.builder()
                .title(dto.getTitle())
                .artistId(existingArtist.getId())
                .releaseYear(dto.getReleaseYear())
                .genre(dto.getGenre())
                .views(dto.getViews())
                .build();

        Song savedSong = songService.save(newSong);
        SongDetailDTO responseDto = new SongDetailDTO(savedSong);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SongDetailDTO> updateSong(@PathVariable UUID id, @RequestBody SongDetailDTO songDTO) {
        return songService.findById(id)
                .map(existingSong -> {
                    Artist existingArtist = fetchArtistById(songDTO.getArtistId());

                    existingSong.setTitle(songDTO.getTitle());
                    existingSong.setArtistId(existingArtist.getId());
                    existingSong.setReleaseYear(songDTO.getReleaseYear());
                    existingSong.setGenre(songDTO.getGenre());
                    existingSong.setViews(songDTO.getViews());

                    Song updatedSong = songService.save(existingSong);
                    return ResponseEntity.ok(new SongDetailDTO(updatedSong));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable UUID id) {
        if (songService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        songService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-artist/{artistId}")
    public ResponseEntity<Void> deleteSongsByArtist(@PathVariable UUID artistId) {
        List<Song> songs = songService.findAll();
        songs.stream()
                .filter(song -> song.getArtistId().equals(artistId))
                .forEach(song -> songService.delete(song));
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/by-artist/{artistId}")
    public ResponseEntity<List<SongCollectionDTO>> getSongsByArtist(@PathVariable UUID artistId) {


        List<SongCollectionDTO> songs = songService.findAll().stream()
                .filter(song -> song.getArtistId().equals(artistId))
                .map(song -> new SongCollectionDTO(song.getId(), song.getTitle()))
                .collect(Collectors.toList());

        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(songs);
    }
    */

    // szczegolowo
    @GetMapping("/by-artist/{artistId}")
    public ResponseEntity<List<SongDetailDTO>> getSongsByArtist(@PathVariable UUID artistId) {
        List<Song> songs = songService.findAll().stream()
                .filter(song -> song.getArtistId().equals(artistId))
                .collect(Collectors.toList());

        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<SongDetailDTO> songDetailDTOs = songs.stream()
                .map(SongDetailDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(songDetailDTOs);
    }


    private Artist fetchArtistById(UUID artistId) {
        try {
            return restTemplate.getForObject(ArtistServiceURL + artistId, Artist.class);
        }
        catch (Exception e) {
            return null;
        }
    }
}
