package com.study.bookmyshow.repositories;

import com.study.bookmyshow.models.SeatTypeShow;
import com.study.bookmyshow.models.Show;
import com.study.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatTypeShowRepository extends JpaRepository<SeatTypeShow, Long> {
    public List<SeatTypeShow> findSeatTypeShowsByShow(Show show);
}
