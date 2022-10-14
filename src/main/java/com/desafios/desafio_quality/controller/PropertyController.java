package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.PropertyRequest;
import com.desafios.desafio_quality.controller.dto.PropertyTotalM2Response;
import com.desafios.desafio_quality.controller.dto.PropertyResponse;
import com.desafios.desafio_quality.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
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
    ResponseEntity<PropertyResponse> findById(@RequestParam Long id) {
        return ResponseEntity.ok(PropertyResponse.toResponse(propertyService.findById(id)));
    }

    @GetMapping("/totalM2")
    public ResponseEntity<PropertyTotalM2Response> getTotalM2PropertyById(@RequestParam Long id){
        PropertyTotalM2Response propertyTotalM2Response = PropertyTotalM2Response.toResponse(propertyService.findById(id));
        propertyTotalM2Response.setTotalM2(propertyService.getTotalM2PropertyById(id));
        return new ResponseEntity<>(propertyTotalM2Response, HttpStatus.OK);
    }

}
