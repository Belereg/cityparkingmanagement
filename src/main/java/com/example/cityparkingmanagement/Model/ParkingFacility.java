package com.example.cityparkingmanagement.Model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "parking_facility")
@Data
public class ParkingFacility {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "random_test_field")
    private String randomTestField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "car_park_id", referencedColumnName = "id")
    private CarPark carPark;

    @OneToOne
    @JoinColumn(name = "bike_rack_id", referencedColumnName = "id")
    private BikeRack bikeRack;

}
