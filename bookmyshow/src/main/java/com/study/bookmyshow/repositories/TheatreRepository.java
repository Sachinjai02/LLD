package com.study.bookmyshow.repositories;

import com.study.bookmyshow.models.Theatre;
import com.study.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
