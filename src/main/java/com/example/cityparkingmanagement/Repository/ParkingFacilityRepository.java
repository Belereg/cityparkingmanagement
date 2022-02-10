package com.example.cityparkingmanagement.Repository;

import com.example.cityparkingmanagement.Model.ParkingFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFacilityRepository extends JpaRepository<ParkingFacility, String> {
}
