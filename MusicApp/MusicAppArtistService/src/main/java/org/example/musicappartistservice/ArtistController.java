package org.example.musicappartistservice;

import org.example.musicappartistservice.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final RestTemplate restTemplate;
    private final String songServiceUrl = "http://localhost:8081/api/songs";

    public ArtistController(ArtistService artistService, RestTemplate restTemplate) {
        this.artistService = artistService;
        this.restTemplate = restTemplate;
    }
    /* // old, 6th labs require more details
    @GetMapping
    public ResponseEntity<List<ArtistCollectionDTO>> getAllArtists() {
        List<ArtistCollectionDTO> artists = artistService.findAll().stream()
                .map(artist -> new ArtistCollectionDTO(artist.getId(), artist.getStagename()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(artists);
    }
     */

    @GetMapping
    public ResponseEntity<List<ArtistDetailDTO>> getAllArtists() {
        List<ArtistDetailDTO> artists = artistService.findAll().stream()
                .map(artist -> new ArtistDetailDTO(artist))
                .collect(Collectors.toList());
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDetailDTO> getArtistById(@PathVariable UUID id) {
        Artist artist = artistService.findById(id);
        if (artist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new ArtistDetailDTO(artist));
    }

    @PostMapping
    public ResponseEntity<ArtistDetailDTO> createArtist(@RequestBody ArtistCreateUpdateDTO artistDTO) {
        Artist newArtist = Artist.builder()
                .id(UUID.randomUUID())
                .name(artistDTO.getName())
                .surname(artistDTO.getSurname())
                .age(artistDTO.getAge())
                .stagename(artistDTO.getStagename())
                .popularity(1)  // Domyślna popularność
                .build();
        Artist savedArtist = artistService.save(newArtist);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ArtistDetailDTO(savedArtist));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ArtistDetailDTO> updateArtist(@PathVariable UUID id, @RequestBody ArtistCreateUpdateDTO artistDTO) {
        Artist artist = artistService.findById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }
        artist.setName(artistDTO.getName());
        artist.setSurname(artistDTO.getSurname());
        artist.setAge(artistDTO.getAge());
        artist.setStagename(artistDTO.getStagename());
        Artist updatedArtist = artistService.save(artist);
        return ResponseEntity.ok(new ArtistDetailDTO(updatedArtist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable UUID id) {
        Artist artist = artistService.findById(id);

        if (artist == null) {
            return ResponseEntity.notFound().build();
        }
        String deleteSongsUrl = songServiceUrl + "/by-artist/" + id;
        restTemplate.delete(deleteSongsUrl);

        artistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/{id}/songs")
    public ResponseEntity<List<SongCollectionDTO>> getSongsByArtist(@PathVariable UUID id) {
        Artist artist = artistService.findById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }
        String getSongsUrl = songServiceUrl + "/by-artist/" + id;
        SongCollectionDTO[] songs = restTemplate.getForObject(getSongsUrl, SongCollectionDTO[].class);
        if(songs == null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(List.of(songs));
    }
    */
    // szczegolowo

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<SongDetailDTO>> getSongsByArtist(@PathVariable UUID id) {
        Artist artist = artistService.findById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }
        String getSongsUrl = songServiceUrl + "/by-artist/" + id;
        SongDetailDTO[] songs = restTemplate.getForObject(getSongsUrl, SongDetailDTO[].class);
        if(songs == null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(List.of(songs));
    }


}

