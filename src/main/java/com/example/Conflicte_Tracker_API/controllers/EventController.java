package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.EventDto;
import com.example.Conflicte_Tracker_API.entities.Event;
import com.example.Conflicte_Tracker_API.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/event")
public class EventController {
    private EventService service ;

    private EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public Event create(EventDto eventDto){
        return service.create(eventDto);
    }

}
