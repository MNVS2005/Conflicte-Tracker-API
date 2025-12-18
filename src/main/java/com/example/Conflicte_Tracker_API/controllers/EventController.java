package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.EventDto;
import com.example.Conflicte_Tracker_API.mapper.EventMapper;
import com.example.Conflicte_Tracker_API.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;
    private final EventMapper mapper;

    public EventController(EventService service, EventMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public EventDto create(@RequestBody EventDto dto) {
        return mapper.toDTO(service.create(dto));
    }

    @PutMapping("/{id}")
    public EventDto update(@PathVariable Long id, @RequestBody EventDto dto) {
        return mapper.toDTO(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
