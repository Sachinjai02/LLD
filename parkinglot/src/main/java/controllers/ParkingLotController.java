package controllers;

import dtos.*;
import models.ParkingLot;
import services.ParkingLotService;

public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public BaseResponseDto<CreateParkingLotResponseData> createParkingLot(CreateParkingLotRequestDto request) {
        ParkingLot parkingLot = parkingLotService.createParkingLot(request.getAddress(), request.getNumberOfFloors());
        BaseResponseDto<CreateParkingLotResponseData> responseDto = new BaseResponseDto<CreateParkingLotResponseData>(new CreateParkingLotResponseData(parkingLot));
        responseDto.setStatus("Success");
        return responseDto;
    }

    public BaseResponseDto<UpdateParkingLotResponseData> updateParkingLotAddress(UpdateParkingLotRequestDto requestDto) {
        ParkingLot parkingLot = parkingLotService.updateParkingLot(requestDto.getParkingLotId(), requestDto.getAddress());
        BaseResponseDto<UpdateParkingLotResponseData> responseDto = new BaseResponseDto<>(new UpdateParkingLotResponseData(parkingLot));
        responseDto.setStatus("Success");
        return responseDto;
    }

}


