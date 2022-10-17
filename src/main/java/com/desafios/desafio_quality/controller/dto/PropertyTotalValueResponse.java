package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * This is used as response in endpoint to send Property and it total price
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PropertyTotalValueResponse {

    /**
     * Property id
     */
    private Long id;
    /**
     * Property name
     */
    private String propName;
    /**
     * Property square area price
     */
    private BigDecimal valueDistrictM2;
    /**
     * Property total price
     */
    private BigDecimal propTotalPrice;

    public static PropertyTotalValueResponse toResponse(Property property) {
        return PropertyTotalValueResponse.builder()
                .id(property.getId())
                .propName(property.getPropName())
                .valueDistrictM2(property.getDistrict().getValueDistrictM2())
                .build();
    }
}
