package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.PropertyRequest;
import com.desafios.desafio_quality.controller.dto.PropertyTotalM2Response;
import com.desafios.desafio_quality.controller.dto.PropertyResponse;
import com.desafios.desafio_quality.controller.dto.PropertyTotalValueResponse;
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


    /**
     * Creates a new Property instance.
     * Contains all validations as specified by the exercise.
     * Must send the Room and the District to create the new instance.
     * Returns 201 CREATED in case operation is success.
     * @param propertyRequest the Property instance.
     * @return a ResponseEntity<Void> instance.
     */
    @PostMapping
    ResponseEntity<Void> create(@RequestBody PropertyRequest propertyRequest) {
        propertyService.save(propertyRequest.toEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Returns a property instance given its ID.
     * Returns 200 OK in case operation is success.
     * @param id the Property ID.
     * @return the Property instance.
     */
    @GetMapping
    ResponseEntity<PropertyResponse> findById(@RequestParam Long id) {
        return ResponseEntity.ok(PropertyResponse.toResponse(propertyService.findById(id)));
    }

    /**
     * Endpoint to get the property price.
     * Returns 200 OK in case operation is success.
     * @param id the Property ID
     * @return the property price
     */
    @GetMapping("/prop-price")
    ResponseEntity<PropertyTotalValueResponse> pricePropertyById(@RequestParam Long id) {
        PropertyTotalValueResponse total = PropertyTotalValueResponse.toResponse(propertyService.findById(id));
        total.setPropTatalPrice(propertyService.pricePropertyById(id));
        return ResponseEntity.ok(total);
    }

    /**
     * Endpoint to get the property square Area.
     * Returns 200 OK in case operation is success.
     * @param id the Property ID
     * @return the property Squared area
     */
    @GetMapping("/totalM2")
    public ResponseEntity<PropertyTotalM2Response> getTotalM2PropertyById(@RequestParam Long id){
        PropertyTotalM2Response propertyTotalM2Response = PropertyTotalM2Response.toResponse(propertyService.findById(id));
        propertyTotalM2Response.setTotalM2(propertyService.getTotalM2PropertyById(id));
        return new ResponseEntity<>(propertyTotalM2Response, HttpStatus.OK);
    }

}
