package org.bsuir.part2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private String title;
    private String author;
    private String description;
    private Boolean available;
    private String category;
}
