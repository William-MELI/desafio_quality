package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PropertyTotalValueResponse {

    private Property property;
    private BigDecimal bigDecimal;

}
