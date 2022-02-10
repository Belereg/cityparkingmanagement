package com.example.cityparkingmanagement.dto.mapper;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.dto.CityDTO;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CityMapperImpl implements MyMapper<City, CityDTO> {
    @Override
    public City dtoToEntity(CityDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        City entity = new City();
        entity.setId(!isNull(dto.getId()) ? dto.getId() : null);
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        return entity;
    }

    @Override
    public CityDTO entityToDto(City entity) {
        if (isNull(entity)) {
            return null;
        }

        CityDTO dto = new CityDTO();
        dto.setId(!isNull(entity.getId()) ? entity.getId() : null);
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        return dto;
    }
}
