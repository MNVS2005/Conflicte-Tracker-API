package com.example.Conflicte_Tracker_API.repository;

import com.example.Conflicte_Tracker_API.entities.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {

}
