package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.PropertyRequest;
import com.desafios.desafio_quality.controller.dto.PropertyTotalM2;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imovel")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping
    ResponseEntity<Void> create(@RequestBody PropertyRequest propertyRequest) {
        propertyService.save(propertyRequest.toEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    Property findById(@RequestParam Long id) {
        return propertyService.findById(id);
    }

    @GetMapping("/totalM2")
    public ResponseEntity<PropertyTotalM2> getTotalM2Property(@RequestParam Long id){
        return new ResponseEntity<>(propertyService.getTotalM2Property(id),HttpStatus.OK);
    }

}
