package org.example.musicappsongservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongCollectionDTO {
    private UUID id;
    private String title;
}
