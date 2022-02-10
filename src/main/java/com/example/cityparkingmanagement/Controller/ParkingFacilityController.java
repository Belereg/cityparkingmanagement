package com.example.cityparkingmanagement.Controller;

import com.example.cityparkingmanagement.Model.ParkingFacility;
import com.example.cityparkingmanagement.Service.ParkingFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/parkingfacility")
@RestController
public class ParkingFacilityController {

    private ParkingFacilityService parkingFacilityService;

    @Autowired
    public ParkingFacilityController(ParkingFacilityService parkingFacilityService) {
        this.parkingFacilityService = parkingFacilityService;
    }

    @PostMapping("/save/{cityId}")
    public ResponseEntity<ParkingFacility> postProgramme(@Validated @RequestBody ParkingFacility parkingFacility, @PathVariable String cityId) {
        ParkingFacility result = parkingFacilityService.save(parkingFacility, cityId);
        return ResponseEntity.ok(result);
    }

}
