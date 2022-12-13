package com.study.bookmyshow.controllers;

import com.study.bookmyshow.models.City;
import com.study.bookmyshow.models.User;
import com.study.bookmyshow.services.CityService;
import com.study.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CityController {

    private CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;

    }

    public City createCity(String cityName) {
        return cityService.createCity(cityName);
    }
}
