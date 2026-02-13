package com.example.Conflicte_Tracker_API.controllers;
import com.example.Conflicte_Tracker_API.service.ConflictService;
import com.example.Conflicte_Tracker_API.entities.Conflict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web/conflicts")
public class WebConflictController {

    private final ConflictService conflictService;

    public WebConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public String listConflicts(Model model) {

        List<Conflict> conflicts = conflictService.findAll();

        model.addAttribute("conflicts", conflicts);

        return "conflicts"; // Thymeleaf buscarà conflicts.html
    }
}