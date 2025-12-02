package com.example.Conflicte_Tracker_API.repository;

import com.example.Conflicte_Tracker_API.entities.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Long> {
}
