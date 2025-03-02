package org.example.musicappartistservice;

import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class Artist implements Comparable<Artist>, Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "stagename")
    private String stagename;

    @Column(name = "popularity")
    private int popularity;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=[" + id + "]" +
                " name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", stagename='" + stagename + '\'' +
                '}';
    }

    @Override
    public int compareTo(Artist a){
        return this.popularity - a.popularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return stagename.equals(artist.stagename) && name.equals(artist.name) && surname.equals(artist.surname) && age == artist.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, stagename);
    }

}
