package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.FactionDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.Country;
import com.example.Conflicte_Tracker_API.entities.Faction;
import com.example.Conflicte_Tracker_API.mapper.FactionMapper;
import com.example.Conflicte_Tracker_API.repository.ConflictRepository;
import com.example.Conflicte_Tracker_API.repository.CountryRepository;
import com.example.Conflicte_Tracker_API.repository.FactionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FactionService {

    private final FactionRepository repo;
    private final ConflictRepository conflictRepo;
    private final CountryRepository countryRepo;
    private final FactionMapper mapper;

    public FactionService(FactionRepository repo,
                          ConflictRepository conflictRepo,
                          CountryRepository countryRepo,
                          FactionMapper mapper) {
        this.repo = repo;
        this.conflictRepo = conflictRepo;
        this.countryRepo = countryRepo;
        this.mapper = mapper;
    }

    // ✅ CREATE
    public FactionDto create(FactionDto dto) {

        Conflict conflict = conflictRepo.findById(dto.conflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found: " + dto.conflictId()));

        Set<Country> countries = new HashSet<>(
                countryRepo.findAllById(dto.supportedCountryIds())
        );

        Faction faction = mapper.toEntity(dto);
        faction.setConflict(conflict);
        faction.setSupportedCountries(countries);

        return mapper.toDTO(repo.save(faction));
    }

    // ✅ READ ALL
    public List<FactionDto> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // ✅ READ BY ID
    public FactionDto findById(Long id) {
        Faction faction = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found: " + id));

        return mapper.toDTO(faction);
    }

    // ✅ UPDATE
    public FactionDto update(Long id, FactionDto dto) {

        Faction existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found: " + id));

        existing.setName(dto.name());

        if (dto.conflictId() != null) {
            Conflict conflict = conflictRepo.findById(dto.conflictId())
                    .orElseThrow(() -> new RuntimeException("Conflict not found: " + dto.conflictId()));
            existing.setConflict(conflict);
        }

        if (dto.supportedCountryIds() != null) {
            Set<Country> countries = new HashSet<>(
                    countryRepo.findAllById(dto.supportedCountryIds())
            );
            existing.setSupportedCountries(countries);
        }

        return mapper.toDTO(repo.save(existing));
    }

    // ✅ DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Faction not found: " + id);
        }
        repo.deleteById(id);
    }
}
