package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.FactionDto;
import com.example.Conflicte_Tracker_API.entities.Faction;
import com.example.Conflicte_Tracker_API.service.FactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/faction")
public class FactionController {

    private FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }
    @GetMapping
    public Iterable<FactionDto> findAll() {
        return factionService.findAll();
    }

    @PostMapping
    public FactionDto create(FactionDto dto) {
        return factionService.create(dto);
    }

}
