package com.example.cityparkingmanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.cityparkingmanagement.dto.Constants.*;

@Data
public class ParkingFacilityDTO {


    @Pattern(regexp = UUID_REGEX, message = INVALID_VALUE)
    private String id;

    @NotNull
    private String randomTestField;

    @NotNull(message = NULL_VALUE)
    @Pattern(regexp = UUID_REGEX, message = INVALID_VALUE)
    private String cityId;

}
