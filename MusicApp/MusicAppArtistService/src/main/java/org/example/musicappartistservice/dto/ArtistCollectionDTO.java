package org.example.musicappartistservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistCollectionDTO {
    private UUID id;
    private String stagename;
}
