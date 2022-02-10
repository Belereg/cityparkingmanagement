package com.example.cityparkingmanagement.Service;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.Repository.CityRepository;
import com.example.cityparkingmanagement.dto.CityDTO;
import com.example.cityparkingmanagement.dto.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.cityparkingmanagement.dto.Constants.INVALID_VALUE;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final MyMapper<City, CityDTO> cityMapper;


    @Autowired
    public CityService(CityRepository cityRepository, MyMapper<City, CityDTO> cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public CityDTO save(CityDTO dto) {
        City city = Optional.ofNullable(cityMapper.dtoToEntity(dto))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_VALUE));

        return Optional.ofNullable(cityRepository.save(city))
                .map(cityMapper::entityToDto)
                .orElse(null);
    }



    public City getCityById(String id) {
        return this.cityRepository.findById(id).get();
    }

    public City getCityByCode(String code) {
        return this.cityRepository.findByCode(code).get();
    }

    public List<City> findAllCities() {
        return this.cityRepository.findAll();
    }

}
