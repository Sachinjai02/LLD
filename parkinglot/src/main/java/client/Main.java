package client;

import controllers.ParkingLotController;
import controllers.TicketController;
import dtos.*;
import models.*;
import registry.ObjectsRegistry;
import repositories.*;
import services.ParkingLotService;
import services.TicketService;
import strategies.NearestFreeSlotAssignmentStrategy;
import strategies.SlotAssignmentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        addControllerAndServices();

        CreateParkingLotRequestDto requestDto = new CreateParkingLotRequestDto();
        requestDto.setAddress("12/234 sector-3 Vasundhara");
        requestDto.setNumberOfFloors(4);

        ParkingLotController parkingLotController = (ParkingLotController) ObjectsRegistry.get("parkingLotController");
        BaseResponseDto<CreateParkingLotResponseData> parkingLotResponse = parkingLotController.createParkingLot(requestDto);
        ParkingLot parkingLot = parkingLotResponse.getData().getParkingLot();
        if(parkingLotResponse.getStatus().equalsIgnoreCase("success")) {
            System.out.println("Error during creation of Parking lot");
        } else {
            System.out.println("Parking lot created " + parkingLot.getId());
        }

        UpdateParkingLotRequestDto updateParkingLotRequestDto = new UpdateParkingLotRequestDto();
        updateParkingLotRequestDto.setParkingLotId(1l);
        updateParkingLotRequestDto.setAddress("12/234 sector-3 Vasundhara Ghaziabad");
        BaseResponseDto<UpdateParkingLotResponseData> updateParkingLotResponseDataDto
                = parkingLotController.updateParkingLotAddress(updateParkingLotRequestDto);
        System.out.println("Update parking lot address " +
                updateParkingLotResponseDataDto.getData().getParkingLot().getAddress());


        Operator operator = new Operator();
        operator.setName("Rocky");
        OperatorRepository operatorRepository = (OperatorRepository) ObjectsRegistry.get("operatorRepository");
        operatorRepository.save(operator);

        Gate gate = new Gate();
        gate.setGateNumber("1");
        gate.setOperator(operator);
        gate.setStatus(GateStatus.CLOSED);
        GateRepository gateRepository = (GateRepository) ObjectsRegistry.get("gateRepository");
        gateRepository.save(gate);

        SlotRepository slotRepository = (SlotRepository) ObjectsRegistry.get("slotRepository");
        parkingLot.getFloors().forEach(floor -> {
            List<ParkingSlot> parkingSlots = new ArrayList<>();
            for(int i=0;i<300;++i) {
                ParkingSlot parkingSlot = new ParkingSlot();
                Random random = new Random();
                if(random.nextBoolean()) {
                    parkingSlot.setStatus(SlotStatus.NOT_AVAILABLE);
                } else {
                    parkingSlot.setStatus(SlotStatus.FREE);
                }
                parkingSlot.setSlotNumber(floor.getFloorNumber() + "-" + (i+1));
                parkingSlot.setType(SlotType.CAR);
                slotRepository.save(parkingSlot);
                parkingSlots.add(parkingSlot);
            }
            floor.setSlots(parkingSlots);
        });
        
        TicketController ticketController = (TicketController) ObjectsRegistry.get("ticketController");
        GenerateTicketRequestDto generateTicketRequestDto = new GenerateTicketRequestDto();
        generateTicketRequestDto.setVehicle(new Vehicle(1l, VehicleType.CAR, "HR12RT7890"));
        generateTicketRequestDto.setEntryGateId(1l);
        generateTicketRequestDto.setParkingLotId(1l);
        generateTicketRequestDto.setDesiredSlotType(SlotType.CAR);
        BaseResponseDto<GenerateTicketResponseData> ticketResponseDto = ticketController.generateTicket(generateTicketRequestDto);
        System.out.println("Vehicle is parked at " + ticketResponseDto.getData().getTicket().getAllotedSlot().getSlotNumber());
    }

    private static void addControllerAndServices() {

        ObjectsRegistry.put("parkingLotRepository", new ParkingLotRepository());
        ObjectsRegistry.put("parkingFloorRepository", new ParkingFloorRepository());
        ObjectsRegistry.put("parkingLotService",
                new ParkingLotService((ParkingLotRepository) ObjectsRegistry.get("parkingLotRepository"),
                        (ParkingFloorRepository) ObjectsRegistry.get("parkingFloorRepository")));

        ObjectsRegistry.put("parkingLotController",
                new ParkingLotController((ParkingLotService) ObjectsRegistry.get("parkingLotService")));

        ObjectsRegistry.put("ticketRepository", new TicketRepository());
        ObjectsRegistry.put("operatorRepository", new OperatorRepository());
        ObjectsRegistry.put("gateRepository", new GateRepository());
        ObjectsRegistry.put("slotRepository", new SlotRepository());
        ObjectsRegistry.put("nearestFreeSlotAssignmentStrategy", new NearestFreeSlotAssignmentStrategy());

        ObjectsRegistry.put("ticketService", new TicketService(
                (TicketRepository) ObjectsRegistry.get("ticketRepository"),
                (GateRepository) ObjectsRegistry.get("gateRepository"),
                (OperatorRepository) ObjectsRegistry.get("operatorRepository"),
                (SlotRepository) ObjectsRegistry.get("slotRepository"),
                (SlotAssignmentStrategy) ObjectsRegistry.get("nearestFreeSlotAssignmentStrategy"),
                (ParkingLotRepository) ObjectsRegistry.get("parkingLotRepository")
        ));
        ObjectsRegistry.put("ticketController", new TicketController((TicketService) ObjectsRegistry.get("ticketService")));
    }
}
