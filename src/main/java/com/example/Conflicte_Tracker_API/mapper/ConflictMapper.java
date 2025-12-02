package com.example.Conflicte_Tracker_API.mapper;

import com.example.Conflicte_Tracker_API.dto.ConflictDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.Country;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ConflictMapper {
    public ConflictDto toDTO(Conflict entity) {
        return new ConflictDto(
                entity.getId(),
                entity.getName(),
                entity.getStartDate(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getCountries().stream().map(Country::getId).collect(Collectors.toSet())
        );
    }

    public Conflict toEntity(ConflictDto dto, Set<Country> countries) {
        Conflict c = new Conflict();
        c.setId(dto.id());
        c.setName(dto.name());
        c.setStartDate(dto.startDate());
        c.setStatus(dto.status());
        c.setDescription(dto.description());
        c.setCountries(countries);
        return c;
    }
}
