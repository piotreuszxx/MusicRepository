package org.example.musicappsongservice;

import org.example.musicappsongservice.Song;
import org.example.musicappsongservice.Artist;
import org.example.musicappsongservice.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Optional<Song> findById(UUID id) {
        return songRepository.findById(id);
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void deleteById(UUID id) {
        songRepository.deleteById(id);
    }

    public void listSongs() {
        List<Song> songs = songRepository.findAll();
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    public void delete(Song song) {
        songRepository.delete(song);
    }




    /*
    public void addSong(String songTitle, String artistStagename, int releaseYear, String genre, int views) {

        Song song = Song.builder()
                .id(UUID.randomUUID())
                .title(songTitle)
                .artist(artistService.findByStagename(artistStagename).get())
                .releaseYear(releaseYear)
                .genre(genre)
                .views(views)
                .build();
        songRepository.save(song);

    }
    */
}

