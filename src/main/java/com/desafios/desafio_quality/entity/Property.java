package com.desafios.desafio_quality.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propName;

    @ManyToOne
    private District district;

    @Transient
    private List<Room> roomList;

    public Property(String propName, District district, List<Room> roomList) {
        this.propName = propName;
        this.district = district;
        this.roomList = roomList;
    }
}
