package com.example.cityparkingmanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.cityparkingmanagement.dto.Constants.*;

@Data
public class CityDTO {


    @Pattern(regexp = UUID_REGEX, message = INVALID_VALUE)
    private String id;

    @NotBlank(message = BLANK_VALUE)
    private String name;

    @NotBlank(message = BLANK_VALUE)
    @Size(max =4, message = CODE_LENGTH_EXCEEDED)
    private String code;

}
