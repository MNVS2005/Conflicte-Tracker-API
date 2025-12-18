package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.ConflictDto;
import com.example.Conflicte_Tracker_API.dto.CountryDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.ConflictStatus;
import com.example.Conflicte_Tracker_API.mapper.ConflictMapper;
import com.example.Conflicte_Tracker_API.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/conflict")
public class ConflictController {

    private  ConflictService service;
    private  ConflictMapper mapper;

    public ConflictController(ConflictService service, ConflictMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // GET /conflicts?status=ACTIVE
    @GetMapping
    public List<ConflictDto> getAll(
            @RequestParam(required = false) ConflictStatus status) {

        return service.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // GET /conflicts/{id}
    @GetMapping("/{id}")
    public ConflictDto getById(@PathVariable Long id) {
        return mapper.toDTO(service.findById(id));
    }

    // POST /conflicts
    @PostMapping
    public ConflictDto create(@RequestBody ConflictDto dto) {
        return mapper.toDTO(service.create(dto));
    }

    // PUT /conflicts/{id}
    @PutMapping("/{id}")
    public ConflictDto update(@PathVariable Long id, @RequestBody ConflictDto dto) {
        return mapper.toDTO(service.update(id, dto));
    }

    // DELETE /conflicts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
