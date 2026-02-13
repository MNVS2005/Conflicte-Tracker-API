package com.example.Conflicte_Tracker_API.repository;

import com.example.Conflicte_Tracker_API.entities.Conflict;
import com.example.Conflicte_Tracker_API.entities.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    List<Conflict> findByStatus(ConflictStatus status);

    @Query("""
        SELECT c FROM Conflict c
        JOIN c.countries co
        WHERE co.code = :code
    """)
    List<Conflict> findByCountryCode(String code);
}
