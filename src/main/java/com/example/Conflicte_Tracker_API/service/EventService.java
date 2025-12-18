package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.EventDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.Event;
import com.example.Conflicte_Tracker_API.repository.ConflictRepository;
import com.example.Conflicte_Tracker_API.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepo;
    private final ConflictRepository conflictRepo;

    public EventService(EventRepository eventRepo, ConflictRepository conflictRepo) {
        this.eventRepo = eventRepo;
        this.conflictRepo = conflictRepo;
    }

    // CREATE
    public Event create(EventDto dto) {
        Event e = new Event();
        e.setEventDate(dto.eventDate());
        e.setLocation(dto.location());
        e.setDescription(dto.description());
        e.setConflict(conflictRepo.findById(dto.conflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found")));
        return eventRepo.save(e);
    }

    // READ ALL
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    // READ BY ID
    public Event findById(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // UPDATE
    public Event update(Long id, EventDto dto) {
        Event e = findById(id);
        e.setEventDate(dto.eventDate());
        e.setLocation(dto.location());
        e.setDescription(dto.description());
        return eventRepo.save(e);
    }

    // DELETE
    public void delete(Long id) {
        if (!eventRepo.existsById(id)) {
            throw new RuntimeException("Event not found");
        }
        eventRepo.deleteById(id);
    }
}
