package org.example.musicappartistservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistCreateUpdateDTO {
    private String name;
    private String surname;
    private int age;
    private String stagename;

}
