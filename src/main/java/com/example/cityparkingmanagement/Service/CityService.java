package com.example.cityparkingmanagement.Service;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.Repository.CityRepository;
import com.example.cityparkingmanagement.dto.CityDTO;
import com.example.cityparkingmanagement.dto.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.example.cityparkingmanagement.dto.Constants.INVALID_VALUE;
import static com.example.cityparkingmanagement.dto.Constants.UUID_REGEX;
import static feign.Util.checkArgument;
import static java.util.Objects.nonNull;

@Service
public class CityService {

    public static final String INVALID_VALUE_TEMPLATE = "Invalid value: %s";
    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile(UUID_REGEX);

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


    public CityDTO getCityById(String id) {
        checkArgument(nonNull(id), String.format(INVALID_VALUE_TEMPLATE, id));
        checkArgument(UUID_REGEX_PATTERN.matcher(id).matches(), String.format(INVALID_VALUE_TEMPLATE, id));

        return cityRepository.findById(id)
                .map(cityMapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s was not found", id)));
    }

    public CityDTO getCityByCode(String code) {
        checkArgument(nonNull(code), String.format(INVALID_VALUE_TEMPLATE, code));

        return cityRepository.findByCode(code)
                .map(cityMapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with code %s was not found", code)));
    }

    public Page<CityDTO> getAllCities() {
        return cityRepository.findAll(Pageable.unpaged()).map(cityMapper::entityToDto);
    }

}
