package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.CountryDto;
import com.example.Conflicte_Tracker_API.mapper.CountryMapper;
import com.example.Conflicte_Tracker_API.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService service;
    private final CountryMapper mapper;

    public CountryController(CountryService service, CountryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public CountryDto create(@RequestBody CountryDto dto) {
        return mapper.toDTO(service.create(dto));
    }

    @GetMapping
    public List<CountryDto> findAll() {
        return service.findAll().stream().map(mapper::toDTO).toList();
    }
}
