package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.FactionDto;
import com.example.Conflicte_Tracker_API.mapper.FactionMapper;
import com.example.Conflicte_Tracker_API.service.FactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class FactionController {
    private final FactionService service;
    private final FactionMapper mapper;

    public FactionController(FactionService service, FactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public FactionDto create(@RequestBody FactionDto dto) {
        return mapper.toDTO(service.create(dto));
    }

    @PutMapping("/{id}")
    public FactionDto update(@PathVariable Long id, @RequestBody FactionDto dto) {
        return mapper.toDTO(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
