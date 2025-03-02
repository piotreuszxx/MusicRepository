package org.example.musicappsongservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class Song implements Comparable<Song>, Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "views")
    private int views;

    @Column(name = "genre")
    private String genre;

    @Column(name = "artist_id")
    private UUID artistId;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=[" + id + "]" +
                " title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", views=" + views +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public int compareTo(Song s){
        // po roku wydania, tytule
        if (this.releaseYear == s.releaseYear) {
            return this.title.compareTo(s.title);
        }
        return this.releaseYear - s.releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return releaseYear == song.releaseYear && views == song.views && title.equals(song.title) && genre.equals(song.genre);
        }

    @Override
    public int hashCode() {
        return title.hashCode() + releaseYear + views + genre.hashCode();
    }
}
