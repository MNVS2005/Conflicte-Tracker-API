package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.ConflictDto;
import com.example.Conflicte_Tracker_API.dto.CountryDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.mapper.ConflictMapper;
import com.example.Conflicte_Tracker_API.service.ConflictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/conflict")
public class ConflictController {

    private  ConflictService conflictService;
    private  ConflictMapper conflictMapper;

    public ConflictController(ConflictService conflictService, ConflictMapper conflictMapper) {
        this.conflictService = conflictService;
        this.conflictMapper = conflictMapper;
    }


    @PostMapping
    public ConflictDto create (@RequestBody ConflictDto conflictDto){
        return conflictMapper.toDTO(conflictService.create(conflictDto));
    }

    @GetMapping
    public List<ConflictDto> findByAll(){
        return conflictService.findAll().stream().map(conflictMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ConflictDto findById(@PathVariable Long id){
        return conflictMapper.toDTO(conflictService.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        conflictService.deleted(id);
    }


}
