package com.example.cityparkingmanagement.Controller;

import com.example.cityparkingmanagement.Model.City;
import com.example.cityparkingmanagement.Service.CityService;
import com.example.cityparkingmanagement.dto.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/city")
@RestController
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/save")
    public ResponseEntity<CityDTO> postCity(@Validated @RequestBody CityDTO cityDTO) {
        CityDTO result = cityService.save(cityDTO);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getById/{cityId}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable String cityId) {
        CityDTO result = cityService.getCityById(cityId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity<CityDTO> getCityByCode(@PathVariable String code) {
        CityDTO result = cityService.getCityByCode(code);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<CityDTO>> getAllCities() {
        Page<CityDTO> result = cityService.getAllCities();
        return ResponseEntity.ok(result);
    }
}
