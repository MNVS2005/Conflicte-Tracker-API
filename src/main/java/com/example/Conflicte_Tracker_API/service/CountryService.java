package com.example.Conflicte_Tracker_API.service;

import com.example.Conflicte_Tracker_API.dto.CountryDto;
import com.example.Conflicte_Tracker_API.entities.Country;
import com.example.Conflicte_Tracker_API.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository repo;

    public CountryService(CountryRepository repo) {
        this.repo = repo;
    }

    public Country create(CountryDto dto) {
        Country c = new Country();
        c.setName(dto.name());
        c.setCode(dto.code());
        return repo.save(c);
    }

    public List<Country> findAll() {
        return repo.findAll();
    }

    public void deleted(Long id){
        if (repo.existsById(id)){
            throw new RuntimeException("Country couldn't found: "+id);
        }
        repo.deleteById(id);
    }
}
