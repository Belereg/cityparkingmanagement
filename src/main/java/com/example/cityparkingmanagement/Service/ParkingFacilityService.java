package com.example.cityparkingmanagement.Service;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.Model.ParkingFacility;
import com.example.cityparkingmanagement.Repository.CityRepository;
import com.example.cityparkingmanagement.Repository.ParkingFacilityRepository;
import com.example.cityparkingmanagement.dto.CityDTO;
import com.example.cityparkingmanagement.dto.ParkingFacilityDTO;
import com.example.cityparkingmanagement.dto.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.example.cityparkingmanagement.dto.Constants.INVALID_VALUE;
import static com.example.cityparkingmanagement.dto.Constants.UUID_REGEX;
import static feign.Util.checkArgument;
import static java.util.Objects.nonNull;

@Service
public class ParkingFacilityService {

    public static final String INVALID_VALUE_TEMPLATE = "Invalid value: %s";
    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile(UUID_REGEX);

    private ParkingFacilityRepository parkingFacilityRepository;
    private CityRepository cityRepository;
    private final MyMapper<ParkingFacility, ParkingFacilityDTO> parkingFacilityMyMapper;

    @Autowired
    public ParkingFacilityService(ParkingFacilityRepository parkingFacilityRepository,
                                  CityRepository cityRepository,
                                  MyMapper<ParkingFacility, ParkingFacilityDTO> parkingFacilityMyMapper) {

        this.parkingFacilityRepository = parkingFacilityRepository;
        this.cityRepository = cityRepository;
        this.parkingFacilityMyMapper = parkingFacilityMyMapper;
    }

    public ParkingFacilityDTO getParkingFacilityById(String id) {
        checkArgument(nonNull(id), String.format(INVALID_VALUE_TEMPLATE, id));
        checkArgument(UUID_REGEX_PATTERN.matcher(id).matches(), String.format(INVALID_VALUE_TEMPLATE, id));


        return parkingFacilityRepository.findById(id)
                .map(parkingFacilityMyMapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s was not found", id)));
    }

    public ParkingFacilityDTO save(ParkingFacilityDTO dto, String cityId) {

        checkId(cityId);
        ParkingFacility parkingFacility = Optional.ofNullable(parkingFacilityMyMapper.dtoToEntity(dto))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_VALUE));

        parkingFacility.setCity(cityRepository.findById(cityId).get());

        return Optional.ofNullable(parkingFacilityRepository.save(parkingFacility))
                .map(parkingFacilityMyMapper::entityToDto)
                .orElse(null);

    }


    public List<ParkingFacilityDTO> getAllParkingFacilitiesByCity(String cityId) {
        List<ParkingFacility> parkingFacilityList = parkingFacilityRepository.findAllByCityId(cityId);
        List<ParkingFacilityDTO> parkingFacilityDTOList = new ArrayList<>();
        parkingFacilityList.forEach(parkingFacility -> parkingFacilityDTOList.add(parkingFacilityMyMapper.entityToDto(parkingFacility)));
        return parkingFacilityDTOList;
    }

    private void checkId(String id) {
        if (nonNull(id)) {
            cityRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s was not found!", id)));
        }
    }

}
