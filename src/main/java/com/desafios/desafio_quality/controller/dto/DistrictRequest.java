package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.District;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class DistrictRequest {

    @NotBlank
    @Size(max = 45)
    private String propDistrict;

    @Positive
    @Digits(integer = 13, fraction = 2)
    private BigDecimal valueDistrictM2;

    public District toEntity() {
        return new District(propDistrict, valueDistrictM2);
    }
}
