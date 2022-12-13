package com.study.bookmyshow.controllers;

import com.study.bookmyshow.dtos.BookCreateResponseDto;
import com.study.bookmyshow.dtos.BookTicketRequestDto;
import com.study.bookmyshow.dtos.ResponseStatus;
import com.study.bookmyshow.models.Ticket;
import com.study.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BookCreateResponseDto bookTicket(BookTicketRequestDto request) {
        Ticket ticket = ticketService.bookTicket(request.getShowId(), request.getShowSeatIds(), request.getUserId());
        BookCreateResponseDto responseDto = new BookCreateResponseDto();
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setData(ticket);
        return responseDto;
    }
}
