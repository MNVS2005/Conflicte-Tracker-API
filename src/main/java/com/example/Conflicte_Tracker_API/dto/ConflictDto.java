package com.example.Conflicte_Tracker_API.dto;

import com.example.Conflicte_Tracker_API.entities.ConflictStatus;

import java.time.LocalDate;
import java.util.Set;

public record ConflictDto(
        Long id,
        String name,
        LocalDate startDate,
        ConflictStatus status,
        String description,
        Set<Long> countryIds
) {}
