package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.EventDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.Event;
import com.example.Conflicte_Tracker_API.repository.ConflictRepository;
import com.example.Conflicte_Tracker_API.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {


        private final EventRepository repo;
        private final ConflictRepository conflictRepo;

        public EventService(EventRepository repo, ConflictRepository conflictRepo) {
            this.repo = repo;
            this.conflictRepo = conflictRepo;
        }

        public Event create(EventDto dto) {
            Conflict conflict = conflictRepo.findById(dto.conflictId())
                    .orElseThrow(() -> new RuntimeException("Conflict not found"));

            Event e = new Event();
            e.setEventDate(dto.eventDate());
            e.setLocation(dto.location());
            e.setDescription(dto.description());
            e.setConflict(conflict);

            return repo.save(e);
        }

}
