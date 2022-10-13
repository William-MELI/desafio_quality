package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.District;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DistrictRequest {

    private String propDistrict;
    private BigDecimal valueDistrictM2;

    public District toEntity() {
        return new District(propDistrict, valueDistrictM2);
    }
}
