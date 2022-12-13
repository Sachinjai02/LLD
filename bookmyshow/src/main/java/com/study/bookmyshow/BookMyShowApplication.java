package com.study.bookmyshow;

import com.study.bookmyshow.controllers.*;
import com.study.bookmyshow.dtos.BookCreateResponseDto;
import com.study.bookmyshow.dtos.BookTicketRequestDto;
import com.study.bookmyshow.models.SeatType;
import com.study.bookmyshow.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

    private CityController cityController;
    private ShowController showController;
    private TheatreController theatreController;
    private TicketController ticketController;
    private UserController userController;

    @Autowired
    public BookMyShowApplication(CityController cityController,
                                 ShowController showController,
                                 TheatreController theatreController,
                                 TicketController ticketController,
                                 UserController userController) {
        this.cityController = cityController;
        this.showController = showController;
        this.theatreController = theatreController;
        this.ticketController = ticketController;
        this.userController = userController;
    }



    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
        System.out.println("Application Started");


    }

    @Override
    public void run(String... args) throws Exception {

        //Create a city
        cityController.createCity("New Delhi");
        //Create a User
        userController.createUser("Sachin Jain", "sachin.jain.nsit@gmail.com");
        //Create a theatre in New Delhi
        theatreController.createTheatre("PVR Cinemas", "Saket, New Delhi", 1l);
        //Create an audi in above theatre
        theatreController.createAuditorium(1l, "FunAudi");
        //Create seats in FunAudi
        theatreController.createSeats(1l,
                new HashMap<SeatType, Integer>() {{ put(SeatType.BALCONY, 40);
                                                    put(SeatType.KLUB, 60);
                                                    put(SeatType.PREMIUM, 30); }});
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        showController.createShow(sdf.parse("2022-12-25 21:00:00"), sdf.parse("2022-12-26 00:00:00")
        , 1l, new HashMap<SeatType, Double>() {{
            put(SeatType.PREMIUM, 450d);
            put(SeatType.BALCONY, 300d);
            put(SeatType.KLUB, 200d);
        }});

        BookTicketRequestDto bookTicketRequestDto1 = new BookTicketRequestDto();
        bookTicketRequestDto1.setShowId(1l);
        bookTicketRequestDto1.setShowSeatIds( Arrays.asList(new Long[] { 24l, 15l, 66l, 97l}));
        bookTicketRequestDto1.setUserId(1l);

        BookTicketRequestDto bookTicketRequestDto2 = new BookTicketRequestDto();
        bookTicketRequestDto2.setShowId(1l);
        bookTicketRequestDto2.setShowSeatIds( Arrays.asList(new Long[] { 44l, 45l, 46l, 37l}));
        bookTicketRequestDto2.setUserId(1l);

        Thread t1 = new Thread(new TicketGenerator(this.ticketController, bookTicketRequestDto1), "Booker1");
        Thread t2 = new Thread(new TicketGenerator(this.ticketController, bookTicketRequestDto2), "Booker2");
        t1.start();
        t2.start();

    }

    public static class TicketGenerator implements Runnable {

        private TicketController ticketController;
        private BookTicketRequestDto bookRequest;
        public TicketGenerator(TicketController ticketController, BookTicketRequestDto bookTicketRequestDto) {
            this.ticketController = ticketController;
            this.bookRequest = bookTicketRequestDto;
        }
        @Override
        public void run() {
            BookCreateResponseDto responseDto = this.ticketController.bookTicket(bookRequest);
            if(responseDto.getData() != null) {
                Ticket ticket = responseDto.getData();

                System.out.println("Thread " + Thread.currentThread().getName() + " ::: " +
                        ticket.getAmount() + " Amount to be paid for Ticket booked at " + ticket.getBookingTime()
                + "for seats " + ticket.getShowSeatList());
            }
        }
    }
}
