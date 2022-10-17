package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.District;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


/**
 * This is used as entry for the District related endpoints.
 * Contains all required validations as specified by the exercise.
 *
 */
@Getter
@Setter
public class DistrictRequest {
    /**
     * District name
     */
    @NotBlank(message = "O nome do bairro não pode estar em branco.")
    @Size(max = 45, message = "O nome do bairro deve ter no máximo 45 caracteres.")
    private String propDistrict;
    /**
     * Price for each Room squared area in this District
     */
    @Positive(message = "O valor do metro quadrado deve ser um valor positivo.")
    @Digits(integer = 13, fraction = 2, message = "O valor do metro quadrado deve ter no máximo 13 dígitos inteiros, e no máximo 2 dígitos decimais.")
    private BigDecimal valueDistrictM2;

    public District toEntity() {
        return new District(propDistrict, valueDistrictM2);
    }
}
