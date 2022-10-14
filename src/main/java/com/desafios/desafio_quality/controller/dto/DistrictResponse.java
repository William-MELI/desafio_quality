package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.District;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponse {

    private Long id;
    private String propDistrict;
    private BigDecimal valueDistrictM2;

    public static DistrictResponse toResponse(District district) {
        return DistrictResponse.builder()
                .id(district.getId())
                .propDistrict(district.getPropDistrict())
                .valueDistrictM2(district.getValueDistrictM2())
                .build();
    }
}
