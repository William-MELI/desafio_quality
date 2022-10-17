package com.desafios.desafio_quality.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propDistrict;
    private BigDecimal valueDistrictM2;

    public District(String propDistrict, BigDecimal valueDistrictM2) {
        this.propDistrict = propDistrict;
        this.valueDistrictM2 = valueDistrictM2;
    }
}
