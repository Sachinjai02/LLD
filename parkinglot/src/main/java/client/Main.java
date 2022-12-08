package client;

import controllers.ParkingLotController;
import dtos.BaseResponseDto;
import dtos.CreateParkingLotRequestDto;
import dtos.CreateParkingLotResponse;
import registry.ObjectsRegistry;
import repositories.ParkingFloorRepository;
import repositories.ParkingLotRepository;
import services.ParkingLotService;

public class Main {

    public static void main(String[] args) {

        addControllerAndServices();

        CreateParkingLotRequestDto requestDto = new CreateParkingLotRequestDto();
        requestDto.setAddress("12/234 sector-3 Vasundhara");
        requestDto.setNumberOfFloors(10);

        ParkingLotController parkingLotController = (ParkingLotController) ObjectsRegistry.get("parkingLotController");
        BaseResponseDto<CreateParkingLotResponse> parkingLot = parkingLotController.createParkingLot(requestDto);

    }

    private static void addControllerAndServices() {

        ObjectsRegistry.put("parkingLotRepository", new ParkingLotRepository());
        ObjectsRegistry.put("parkingFloorRepository", new ParkingFloorRepository());
        ObjectsRegistry.put("parkingLotService",
                new ParkingLotService((ParkingLotRepository) ObjectsRegistry.get("parkingLotRepository"),
                        (ParkingFloorRepository) ObjectsRegistry.get("parkingFloorRepository")));

        ObjectsRegistry.put("parkingLotController",
                new ParkingLotController((ParkingLotService) ObjectsRegistry.get("parkingLotService")));


    }
}
