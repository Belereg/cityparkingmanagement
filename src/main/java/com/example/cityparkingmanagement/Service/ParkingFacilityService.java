package com.example.cityparkingmanagement.Service;

import com.example.cityparkingmanagement.Model.ParkingFacility;
import com.example.cityparkingmanagement.Repository.ParkingFacilityRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingFacilityService {

    private ParkingFacilityRepository parkingFacilityRepository;

    public ParkingFacilityService(ParkingFacilityRepository parkingFacilityRepository) {
        this.parkingFacilityRepository = parkingFacilityRepository;
    }

    public ParkingFacility getParkingFacilityById(String id) {
        return this.parkingFacilityRepository.findById(id).get();
    }

    public ParkingFacility save(ParkingFacility parkingFacility, String cityId) {
        //TODO: fa ceva cu cityId (o sa fie matchuit cu cityId din dto)
        return parkingFacilityRepository.save(parkingFacility);
    }
}
