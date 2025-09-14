package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Movie;
import com.bookmyshow.bookmyshow.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheatreId(Long theatreId);

}
