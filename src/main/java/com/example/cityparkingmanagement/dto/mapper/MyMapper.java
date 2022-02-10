package com.example.cityparkingmanagement.dto.mapper;

public interface MyMapper<E, D> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);
}

