package com.example.Conflicte_Tracker_API.repository;

import com.example.Conflicte_Tracker_API.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
