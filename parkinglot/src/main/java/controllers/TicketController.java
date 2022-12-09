package controllers;

import dtos.BaseResponseDto;
import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseData;
import models.Ticket;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BaseResponseDto<GenerateTicketResponseData> generateTicket(GenerateTicketRequestDto requestDto) {
        Ticket ticket = this.ticketService.generateTicket(requestDto.getEntryGateId(),
                requestDto.getVehicle(), requestDto.getDesiredSlotType(),
                requestDto.getParkingLotId());
        BaseResponseDto<GenerateTicketResponseData> responseDto = new BaseResponseDto<>(new GenerateTicketResponseData(ticket));
        responseDto.setStatus("Success");
        return responseDto;
    }
}
