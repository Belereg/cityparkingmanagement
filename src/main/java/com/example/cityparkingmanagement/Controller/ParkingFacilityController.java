package com.example.cityparkingmanagement.Controller;

import com.example.cityparkingmanagement.Model.ParkingFacility;
import com.example.cityparkingmanagement.Service.ParkingFacilityService;
import com.example.cityparkingmanagement.dto.ParkingFacilityDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parkingfacility")
@RestController
public class ParkingFacilityController {

    private ParkingFacilityService parkingFacilityService;

    @Autowired
    public ParkingFacilityController(ParkingFacilityService parkingFacilityService) {
        this.parkingFacilityService = parkingFacilityService;
    }

    @PostMapping("/save/{cityId}")
    public ResponseEntity<ParkingFacilityDTO> postParkingFacility(@Validated @RequestBody ParkingFacilityDTO parkingFacility, @PathVariable String cityId) {
        ParkingFacilityDTO result = parkingFacilityService.save(parkingFacility, cityId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{parkingFacilityId}")
    public ResponseEntity<ParkingFacilityDTO> getParkingFacilityById(@PathVariable String parkingFacilityId) {
        ParkingFacilityDTO result = parkingFacilityService.getParkingFacilityById(parkingFacilityId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllByCityId/{cityId}")
    public ResponseEntity<List<ParkingFacilityDTO>> getAllParkingFacilitiesByCity(@PathVariable String cityId){
        List<ParkingFacilityDTO> result = parkingFacilityService.getAllParkingFacilitiesByCity(cityId);
        return ResponseEntity.ok(result);
    }
}
