package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.ConflictDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.ConflictStatus;
import com.example.Conflicte_Tracker_API.entities.Country;
import com.example.Conflicte_Tracker_API.repository.ConflictRepository;
import com.example.Conflicte_Tracker_API.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ConflictService {


    private final ConflictRepository conflictRepo;
    private final CountryRepository countryRepo;

    public ConflictService(ConflictRepository conflictRepo, CountryRepository countryRepo) {
        this.conflictRepo = conflictRepo;
        this.countryRepo = countryRepo;
    }

    // CREATE
    public Conflict create(ConflictDto dto) {
        Conflict c = new Conflict();
        c.setName(dto.name());
        c.setStartDate(dto.startDate());
        c.setStatus(dto.status());
        c.setDescription(dto.description());
        c.setCountries(new HashSet<>(countryRepo.findAllById(dto.countryIds())));
        return conflictRepo.save(c);
    }

    // READ ALL
    public List<Conflict> findAll() {
        return conflictRepo.findAll();
    }

    // READ BY ID
    public Conflict findById(Long id) {
        return conflictRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found"));
    }

    // UPDATE
    public Conflict update(Long id, ConflictDto dto) {
        Conflict c = findById(id);
        c.setName(dto.name());
        c.setStartDate(dto.startDate());
        c.setStatus(dto.status());
        c.setDescription(dto.description());
        c.setCountries(new HashSet<>(countryRepo.findAllById(dto.countryIds())));
        return conflictRepo.save(c);
    }

    // DELETE
    public void delete(Long id) {
        if (!conflictRepo.existsById(id)) {
            throw new RuntimeException("Conflict not found");
        }
        conflictRepo.deleteById(id);
    }

    // FILTER BY STATUS
    public List<Conflict> findByStatus(ConflictStatus status) {
        return conflictRepo.findByStatus(status);
    }


}

