package com.study.bookmyshow.services;

import com.study.bookmyshow.models.City;
import com.study.bookmyshow.repositories.CityRepository;
import com.study.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;
    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public City createCity(String cityName) {
        City city = new City();
        city.setName(cityName);
        return cityRepository.save(city);
    }
}
