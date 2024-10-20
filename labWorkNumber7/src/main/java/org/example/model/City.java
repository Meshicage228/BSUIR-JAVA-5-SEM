package org.example.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class City {
    private Long id;
    private String title;
    private Float square;
    private String foundationYear;
    List<CitizenTypes> citizenTypesList;
}
