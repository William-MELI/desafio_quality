package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.repository.PropertyRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imovel")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @PostMapping
    Property create(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    @GetMapping
    Property findById(@RequestParam Long id) {
        return this.propertyRepository.findById(id).get();
    }

}
