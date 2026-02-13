package com.example.Conflicte_Tracker_API.mapper;

import com.example.Conflicte_Tracker_API.dto.EventDto;
import com.example.Conflicte_Tracker_API.entities.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

        public EventDto toDTO(Event e) {
            return new EventDto(
                    e.getId(),
                    e.getEventDate(),
                    e.getLocation(),
                    e.getDescription(),
                    e.getConflict() != null ? e.getConflict().getId() : null
            );
        }
}
