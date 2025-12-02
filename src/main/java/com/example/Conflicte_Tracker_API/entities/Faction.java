package com.example.Conflicte_Tracker_API.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;

    @ManyToMany
    @JoinTable(
            name = "faction_country",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> supportedCountries = new HashSet<>();

    // Getters, setters, constructors

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
    }

    public Set<Country> getSupportedCountries() {
        return supportedCountries;
    }

    public void setSupportedCountries(Set<Country> supportedCountries) {
        this.supportedCountries = supportedCountries;
    }
}
