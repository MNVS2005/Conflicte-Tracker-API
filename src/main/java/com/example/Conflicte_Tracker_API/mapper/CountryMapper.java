package com.example.Conflicte_Tracker_API.mapper;

import com.example.Conflicte_Tracker_API.dto.CountryDto;
import com.example.Conflicte_Tracker_API.entities.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public CountryDto toDTO(Country c) {
        return new CountryDto(c.getId(), c.getName(), c.getCode());
    }
}
