package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Movie;
import com.bookmyshow.bookmyshow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieId(Long id);

    List<Show> findByScreenId(Long screenId);

    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Show> findByMovie_IdAndScreen_Theatre_City(Long movieId, String city);


}
