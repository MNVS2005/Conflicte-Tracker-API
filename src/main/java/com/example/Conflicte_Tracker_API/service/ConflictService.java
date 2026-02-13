package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.ConflictDto;
import com.example.Conflicte_Tracker_API.entities.Conflict;
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

    private  ConflictRepository repo;
    private  CountryRepository countryRepo;


    public Conflict create(ConflictDto dto) {
        Set<Country> countries = new HashSet<>(countryRepo.findAllById(dto.countryIds()));
        Conflict c = new Conflict();
        c.setName(dto.name());
        c.setStatus(dto.status());
        c.setStartDate(dto.startDate());
        c.setDescription(dto.description());
        c.setCountries(countries);
        return repo.save(c);
    }

    public List<Conflict> findAll() {
        return repo.findAll();
    }

    public Optional<Conflict> findById(Long id) {
        return repo.findById(id);
    }

    public void deleted(Long id){
        if(repo.existsById(id)){
            throw new RuntimeException("Conflict not found: "+ id);
        }
        repo.deleteById(id);
    }
}

