package com.example.cityparkingmanagement.Repository;

import com.example.cityparkingmanagement.Model.ParkingFacility;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingFacilityRepository extends JpaRepository<ParkingFacility, String> {

    public List<ParkingFacility> findAllByCityId(String cityId);
}
