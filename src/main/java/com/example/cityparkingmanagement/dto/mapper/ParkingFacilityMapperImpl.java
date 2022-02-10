package com.example.cityparkingmanagement.dto.mapper;

import com.example.cityparkingmanagement.Model.ParkingFacility;
import com.example.cityparkingmanagement.dto.ParkingFacilityDTO;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ParkingFacilityMapperImpl implements MyMapper<ParkingFacility, ParkingFacilityDTO> {
    @Override
    public ParkingFacility dtoToEntity(ParkingFacilityDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        ParkingFacility entity = new ParkingFacility();
        entity.setId(!isNull(dto.getId()) ? dto.getId() : null);
        entity.setRandomTestField(dto.getRandomTestField());
        return entity;
    }

    @Override
    public ParkingFacilityDTO entityToDto(ParkingFacility entity) {
        if (isNull(entity)) {
            return null;
        }

        ParkingFacilityDTO dto = new ParkingFacilityDTO();
        dto.setId(!isNull(entity.getId()) ? entity.getId() : null);
        dto.setRandomTestField(entity.getRandomTestField());
        dto.setCityId(entity.getCity().getId());
        return dto;
    }
}
