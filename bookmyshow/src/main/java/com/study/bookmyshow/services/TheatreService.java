package com.study.bookmyshow.services;

import com.study.bookmyshow.models.*;
import com.study.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TheatreService {

    private TheatreRepository theatreRepository;
    private CityRepository cityRepository;

    private AuditoriumRepository auditoriumRepository;

    private SeatRepository seatRepository;

    @Autowired
    public TheatreService(TheatreRepository theatreRepository, CityRepository cityRepository,
                          AuditoriumRepository auditoriumRepository,
                          SeatRepository seatRepository
                            ) {
        this.theatreRepository = theatreRepository;
        this.cityRepository = cityRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
    }


    public Theatre createTheatre(String name, String address, Long cityId) {
        Optional<City> city = this.cityRepository.findById(cityId);
        Theatre theatre = new Theatre();
        theatre.setAddress(address);
        theatre.setName(name);
        theatre.setCity(city.get());
        return theatreRepository.save(theatre);
    }

    public Auditorium createAuditorium(Long theatreId, String name) {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium = this.auditoriumRepository.save(auditorium);
        Optional<Theatre> theatre = this.theatreRepository.findById(theatreId);
        theatre.get().getAuditoriumList().add(auditorium);
        theatreRepository.save(theatre.get());
        return auditorium;
    }


    public List<Seat> createSeats(Long auditoriumId, Map<SeatType, Integer> seatTypeCounts) {
        Auditorium audi = this.auditoriumRepository.findById(auditoriumId).get();
        List<Seat> seats = new ArrayList<>();
        for(Map.Entry<SeatType, Integer> seatTypeCount : seatTypeCounts.entrySet()) {
            SeatType seatType = seatTypeCount.getKey();
            for(int i=1;i<=seatTypeCount.getValue();++i) {
                Seat seat = new Seat();
                seat.setName(seatType.name() + i);
                seat.setSeatType(seatType);
                seat.setStatus(SeatStatus.AVAILABLE);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
        audi.setSeatList(seats);
        audi = auditoriumRepository.save(audi);
        return seats;
    }
}
