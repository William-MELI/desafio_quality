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

/**
 * The main Property entity
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class District {

    /**
     * District ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * District name
     */
    private String propDistrict;

    /**
     * Price for each Room squared area in this District
     */
    private BigDecimal valueDistrictM2;

    public District(String propDistrict, BigDecimal valueDistrictM2) {
        this.propDistrict = propDistrict;
        this.valueDistrictM2 = valueDistrictM2;
    }
}
