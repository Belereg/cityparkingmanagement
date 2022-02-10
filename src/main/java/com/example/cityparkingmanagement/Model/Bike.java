package com.example.cityparkingmanagement.Model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "bike")
@Data
public class Bike {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "is_parked")
    private Boolean isParked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "parking_facility_id", referencedColumnName = "id")
    private ParkingFacility parkingFacility;
}
