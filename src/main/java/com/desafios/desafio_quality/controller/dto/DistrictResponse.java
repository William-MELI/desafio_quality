package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.District;
import lombok.*;

import java.math.BigDecimal;

/**
 * This is used as response for District related endpoints
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponse {

    /**
     * District ID
     */
    private Long id;
    /**
     * District name
     */
    private String propDistrict;
    /**
     * Price for each Room squared area in this District
     */
    private BigDecimal valueDistrictM2;

    public static DistrictResponse toResponse(District district) {
        return DistrictResponse.builder()
                .id(district.getId())
                .propDistrict(district.getPropDistrict())
                .valueDistrictM2(district.getValueDistrictM2())
                .build();
    }
}
