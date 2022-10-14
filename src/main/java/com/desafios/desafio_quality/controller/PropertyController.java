package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.PropertyRequest;
import com.desafios.desafio_quality.controller.dto.PropertyResponse;
import com.desafios.desafio_quality.controller.dto.PropertyTotalValueResponse;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import javax.validation.Valid;


@RestController
@RequestMapping("/imovel")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid PropertyRequest propertyRequest) {
        propertyService.save(propertyRequest.toEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<PropertyResponse> findById(@RequestParam Long id) {
        return ResponseEntity.ok(PropertyResponse.toResponse(propertyService.findById(id)));
    }

    @GetMapping("/prop-price")
    ResponseEntity<PropertyTotalValueResponse> pricePropertyById(@RequestParam Long id) {
        PropertyTotalValueResponse total = PropertyTotalValueResponse.toResponse(propertyService.findById(id));
        total.setPropTatalPrice(propertyService.pricePropertyById(id));
        return ResponseEntity.ok(total);
    }

}
