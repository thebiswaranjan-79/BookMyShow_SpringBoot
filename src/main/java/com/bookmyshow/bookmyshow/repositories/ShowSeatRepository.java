package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Show;
import com.bookmyshow.bookmyshow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShowId(Long id);

    List<ShowSeat> findByShowIdAndStatus(Long showId, String status);


}
