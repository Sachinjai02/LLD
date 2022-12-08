package controllers;

import dtos.BaseResponseDto;
import dtos.CreateParkingLotRequestDto;
import dtos.CreateParkingLotResponse;
import models.ParkingLot;
import services.ParkingLotService;

public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public BaseResponseDto<CreateParkingLotResponse> createParkingLot(CreateParkingLotRequestDto request) {
        ParkingLot parkingLot = parkingLotService.createParkingLot(request.getAddress(), request.getNumberOfFloors());
        BaseResponseDto<CreateParkingLotResponse> baseResponse = new BaseResponseDto<CreateParkingLotResponse>(new CreateParkingLotResponse(parkingLot));
        baseResponse.setStatus("Success");
        return baseResponse;
    }

}


