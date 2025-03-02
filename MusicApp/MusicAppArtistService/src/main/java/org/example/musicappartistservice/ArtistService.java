package org.example.musicappartistservice;

import org.example.musicappartistservice.dto.ArtistCreateUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // znajdz artyste po nazwie (stagename)
    public Optional<Artist> findByStagename(String stagename) {
        return artistRepository.findByStagename(stagename);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist findById(UUID id) {
        return artistRepository.findById(id).orElse(null);
    }

    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteById(UUID id) {
        artistRepository.deleteById(id);
    }

    public void listArtists() {
        List<Artist> artists = artistRepository.findAll();
        for (Artist artist : artists) {
            System.out.println(artist);
        }
    }

    public Artist createArtist(ArtistCreateUpdateDTO artistDTO) {
        Artist artist = Artist.builder()
                .id(UUID.randomUUID()) // nowe UUID
                .stagename(artistDTO.getStagename())
                .name(artistDTO.getName())
                .surname(artistDTO.getSurname())
                .build();
        return artistRepository.save(artist);
    }

}
