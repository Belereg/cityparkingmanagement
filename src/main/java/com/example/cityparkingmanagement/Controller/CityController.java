package com.example.cityparkingmanagement.Controller;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.Service.CityService;
import com.example.cityparkingmanagement.dto.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/city")
@RestController
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/save")
    public ResponseEntity<CityDTO> postProgramme(@Validated @RequestBody CityDTO cityDTO) {
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.ok(result);
    }
}
