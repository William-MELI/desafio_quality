package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PropertyTotalValueResponse {

    private Long id;
    private String propName;
    private BigDecimal valueDistrictM2;
    private BigDecimal propTatalPrice;

    public static PropertyTotalValueResponse toResponse(Property property) {
        return PropertyTotalValueResponse.builder()
                .id(property.getId())
                .propName(property.getPropName())
                .valueDistrictM2(property.getDistrict().getValueDistrictM2())
                .build();
    }
}
