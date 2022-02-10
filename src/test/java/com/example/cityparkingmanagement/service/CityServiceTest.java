package com.example.cityparkingmanagement.service;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.Repository.CityRepository;
import com.example.cityparkingmanagement.Service.CityService;
import com.example.cityparkingmanagement.dto.CityDTO;
import com.example.cityparkingmanagement.dto.mapper.CityMapperImpl;
import com.example.cityparkingmanagement.dto.mapper.MyMapper;
import org.apache.catalina.Pipeline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    private final static String ID = "9a6a650c7ee3df30017ee3df827e0000";
    private final static String CODE = "1234";
    private final static String NAME = "Bucharest";

    private CityRepository cityRepositoryMock = mock(CityRepository.class);
    private MyMapper<City, CityDTO> mapper = new CityMapperImpl();

    private CityService service = new CityService(cityRepositoryMock, mapper);


    @Test
    void getCityByIdHappyFlow() {
        City city = new City();
        city.setId(ID);
        city.setCode(CODE);
        city.setName(NAME);

        when(cityRepositoryMock.findById(city.getId())).thenReturn(Optional.of(city));

        CityDTO cityDTO = service.getCityById(city.getId());

        assertNotNull(cityDTO);
        assertEquals(city.getId(), cityDTO.getId());
        assertEquals(city.getName(), cityDTO.getName());
        assertEquals(city.getCode(), cityDTO.getCode());
        verify(cityRepositoryMock, times(1)).findById(any(String.class));
    }
}
