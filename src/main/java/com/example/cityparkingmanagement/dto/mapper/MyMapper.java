package com.example.cityparkingmanagement.dto.mapper;

import org.apache.catalina.mapper.Mapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MyMapper<E, D> {

    Log log = LogFactory.getLog(Mapper.class);

    E dtoToEntity(D dto);

    D entityToDto(E entity);

    default E updateEntityWithMapValues(Map<String, Object> values, E entity, @Nullable Set<String> exemptFieldNames) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (MapUtils.isEmpty(values)) {
            return entity;
        }
        // it gets the field names from the entity class and super class(if any)
        Set<String> fieldNames = Stream.of(entity.getClass().getDeclaredFields(),
                entity.getClass().getSuperclass().getDeclaredFields())
                .flatMap(Arrays::stream)
                .map(Field::getName)
                .collect(Collectors.toSet());

        // if there are fields that should not be populated remove them
        if (CollectionUtils.isNotEmpty(exemptFieldNames)) {
            fieldNames.removeAll(exemptFieldNames);
        }

        //mutate given entity with given values
        values.entrySet().stream()
                .filter(entry -> fieldNames.contains(entry.getKey()))
                .filter(this::validateEntry)
                .forEach(entry -> {
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(entry.getKey(), entity.getClass());
                        pd.getWriteMethod().invoke(entity, pd.getPropertyType().cast(entry.getValue()));
                    } catch (IntrospectionException | IllegalAccessException | InvocationTargetException ex) {
                        log.error(ex.getMessage());
                    }
                });
        return entity;
    }

    private boolean validateEntry(Map.Entry<String, Object> entry) {
        return entry.getValue().getClass().equals(String.class) ? StringUtils.isNotEmpty((String) entry.getValue()) : true;
    }
}

