package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Show;
import com.bookmyshow.bookmyshow.model.Theatre;
import com.bookmyshow.bookmyshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);



}
