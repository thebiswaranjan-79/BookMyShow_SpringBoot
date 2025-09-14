package com.bookmyshow.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private  Long id;
    private String title;
    private String genre;
    private int durationMins; // in minutes
    private String language;
    private  String releaseDate; // e.g., "2023-10-15"
    private String description;
    private String posterUrl; // URL to the movie poster image
}
