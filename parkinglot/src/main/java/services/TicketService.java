package services;

import models.*;
import repositories.*;
import strategies.SlotAssignmentStrategy;

import java.util.Date;

public class TicketService {

    private TicketRepository ticketRepository;

    private GateRepository gateRepository;

    private OperatorRepository operatorRepository;

    private SlotRepository slotRepository;
    private SlotAssignmentStrategy slotAssignmentStrategy;
    private ParkingLotRepository parkingLotRepository;
    public TicketService(TicketRepository ticketRepository, GateRepository gateRepository
                        , OperatorRepository operatorRepository
                         , SlotRepository slotRepository
                         , SlotAssignmentStrategy strategy
                         , ParkingLotRepository parkingLotRepository
                        ) {
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.slotRepository = slotRepository;
        this.operatorRepository = operatorRepository;
        this.slotAssignmentStrategy = strategy;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket generateTicket(Long gateId, Vehicle vehicle, SlotType desiredSlotType, Long parkingLotId) {
        Ticket ticket = new Ticket();
        Gate gate = gateRepository.getById(gateId);
        ParkingLot parkingLot = parkingLotRepository.getById(parkingLotId);
        ticket.setEntryGate(gate);
        ticket.setOperator(operatorRepository.getById(gate.getOperator().getId()));
        ticket.setEntryTime(new Date());
        ticket.setVehicle(vehicle);
        slotAssignmentStrategy.reserveSlot(parkingLot.getFloors(), desiredSlotType, ticket);
        //slot should get the Vehicle, status should be changed to RESERVED and so on..
        slotRepository.update(ticket.getAllotedSlot());
        ticketRepository.save(ticket);
        return ticket;
    }
}
