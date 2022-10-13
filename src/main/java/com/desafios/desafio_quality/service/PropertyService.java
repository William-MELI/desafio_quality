package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    public Property findById(Long id) {
        return propertyRepository.findById(id).orElseThrow();
    }

}
