package org.example.model;

import lombok.*;
import org.example.enums.CitizenType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CitizenTypes {
    private Long id;
    private CitizenType type;
    private String spokenLanguage;
    private Long population;
    private Long cityId;
}
