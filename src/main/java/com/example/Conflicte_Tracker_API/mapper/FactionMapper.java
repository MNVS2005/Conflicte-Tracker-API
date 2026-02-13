package com.example.Conflicte_Tracker_API.mapper;

import com.example.Conflicte_Tracker_API.dto.FactionDto;
import com.example.Conflicte_Tracker_API.entities.Country;
import com.example.Conflicte_Tracker_API.entities.Faction;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FactionMapper {
    public FactionDto toDTO(Faction f) {
        return new FactionDto(
                f.getId(),
                f.getName(),
                f.getConflict() != null ? f.getConflict().getId() : null,
                f.getSupportedCountries().stream().map(Country::getId).collect(Collectors.toSet())
        );
    }
    public Faction toEntity(FactionDto dto) {
        Faction f = new Faction();
        f.setName(dto.name());
        return f;
    }
}
