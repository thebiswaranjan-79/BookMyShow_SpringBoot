package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Booking;
import com.bookmyshow.bookmyshow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByLanguage(String language);

    List<Movie> findByGenre(String  genre);

    List<Movie> findByTitleContaining(String title);

}
