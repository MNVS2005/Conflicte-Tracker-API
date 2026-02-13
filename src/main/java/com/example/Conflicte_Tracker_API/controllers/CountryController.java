package com.example.Conflicte_Tracker_API.controllers;

import com.example.Conflicte_Tracker_API.dto.CountryDto;
import com.example.Conflicte_Tracker_API.entities.Country;
import com.example.Conflicte_Tracker_API.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/country")
public class CountryController {

     private CountryService countryService;
     private CountryController(CountryService countryService) {
         this.countryService = countryService;
     }

     @PostMapping
     public Country create(CountryDto countryDto){
         return countryService.create(countryDto);
     }

     @GetMapping
        public java.util.List<Country> findAll(){
            return countryService.findAll();
        }

}
