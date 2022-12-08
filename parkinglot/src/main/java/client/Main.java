package client;

import controllers.ParkingLotController;
import dtos.*;
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
        BaseResponseDto<CreateParkingLotResponseData> parkingLotResponse = parkingLotController.createParkingLot(requestDto);
        if(parkingLotResponse.getStatus().equalsIgnoreCase("success")) {
            System.out.println("Error during creation of Parking lot");
        } else {
            System.out.println("Parking lot created " + parkingLotResponse.getData().getParkingLot().getId());
        }

        UpdateParkingLotRequestDto updateParkingLotRequestDto = new UpdateParkingLotRequestDto();
        updateParkingLotRequestDto.setParkingLotId(1l);
        updateParkingLotRequestDto.setAddress("12/234 sector-3 Vasundhara Ghaziabad");
        BaseResponseDto<UpdateParkingLotResponseData> updateParkingLotResponseDataDto
                = parkingLotController.updateParkingLotAddress(updateParkingLotRequestDto);
        System.out.println("Update parking lot address " +
                updateParkingLotResponseDataDto.getData().getParkingLot().getAddress());
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
