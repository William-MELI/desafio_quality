package com.desafios.desafio_quality.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * The main Property entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    /**
     * Property ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Property name
     */
    private String propName;
    /**
     * District reference.
     * It is an N-1 relationship
     */

    @ManyToOne
    private District district;
    /**
     * List of Room reference.
     * This property is @Transient
     */
    @Transient
    private List<Room> roomList;

    public Property(String propName, District district, List<Room> roomList) {
        this.propName = propName;
        this.district = district;
        this.roomList = roomList;
    }
}
