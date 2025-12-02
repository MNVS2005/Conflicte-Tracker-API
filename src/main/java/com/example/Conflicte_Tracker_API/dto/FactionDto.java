package com.example.Conflicte_Tracker_API.dto;

import java.util.Set;

public record FactionDto(
        Long id,
        String name,
        Long conflictId,
        Set<Long> supportedCountryIds
) {
}
