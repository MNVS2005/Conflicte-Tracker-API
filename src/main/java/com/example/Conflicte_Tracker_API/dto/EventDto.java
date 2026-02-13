package com.example.Conflicte_Tracker_API.dto;

import java.time.LocalDate;

public record EventDto(
       Long id,
       LocalDate eventDate,
       String location,
       String description,
       Long conflictId
) {
}
