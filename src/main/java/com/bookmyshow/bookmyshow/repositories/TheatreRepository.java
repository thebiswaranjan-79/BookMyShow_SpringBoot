package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Show;
import com.bookmyshow.bookmyshow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    List<Theatre> findByShowId(String city);




}
