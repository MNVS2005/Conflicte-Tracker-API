package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.FactionDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.Country;
import com.example.Conflicte_Tracker_API.entities.Faction;
import com.example.Conflicte_Tracker_API.repository.ConflictRepository;
import com.example.Conflicte_Tracker_API.repository.CountryRepository;
import com.example.Conflicte_Tracker_API.repository.FactionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FactionService {
    private final FactionRepository factionRepo;
    private final ConflictRepository conflictRepo;
    private final CountryRepository countryRepo;

    public FactionService(FactionRepository factionRepo,
                          ConflictRepository conflictRepo,
                          CountryRepository countryRepo) {
        this.factionRepo = factionRepo;
        this.conflictRepo = conflictRepo;
        this.countryRepo = countryRepo;
    }

    // CREATE
    public Faction create(FactionDto dto) {
        Faction f = new Faction();
        f.setName(dto.name());
        f.setConflict(conflictRepo.findById(dto.conflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found")));
        f.setSupportedCountries(new HashSet<>(countryRepo.findAllById(dto.supportedCountryIds())));
        return factionRepo.save(f);
    }

    // READ ALL
    public List<Faction> findAll() {
        return factionRepo.findAll();
    }

    // READ BY ID
    public Faction findById(Long id) {
        return factionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found"));
    }

    // UPDATE
    public Faction update(Long id, FactionDto dto) {
        Faction f = findById(id);
        f.setName(dto.name());
        f.setSupportedCountries(new HashSet<>(countryRepo.findAllById(dto.supportedCountryIds())));
        return factionRepo.save(f);
    }

    // DELETE
    public void delete(Long id) {
        if (!factionRepo.existsById(id)) {
            throw new RuntimeException("Faction not found");
        }
        factionRepo.deleteById(id);
    }
}
