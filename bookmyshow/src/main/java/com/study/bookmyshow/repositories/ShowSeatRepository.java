package com.study.bookmyshow.repositories;

import com.study.bookmyshow.models.Show;
import com.study.bookmyshow.models.ShowSeat;
import com.study.bookmyshow.models.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select id from show_seat where id in (:showSeatIds)")
    List<ShowSeat> findAllByIdForUpdate(Iterable<Long> showSeatIds);
}
