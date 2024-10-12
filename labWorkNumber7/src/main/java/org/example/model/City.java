package org.example.model;

import lombok.*;

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
}
