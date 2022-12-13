package com.study.bookmyshow.repositories;

import com.study.bookmyshow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
