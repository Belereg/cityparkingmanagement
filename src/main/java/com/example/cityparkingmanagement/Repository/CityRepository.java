package com.example.cityparkingmanagement.Repository;

import com.example.cityparkingmanagement.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    Optional<City> findByCode(String code);

}
