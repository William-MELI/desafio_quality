package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.service.PropertyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imovel")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping
    Property create(@RequestBody Property property) {
        return propertyService.save(property);
    }

    @GetMapping
    Property findById(@RequestParam Long id) {
        return propertyService.findById(id);
    }



}
