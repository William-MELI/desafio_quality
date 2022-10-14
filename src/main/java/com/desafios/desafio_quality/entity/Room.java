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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    private Double roomWidth;
    private Double roomLength;

    @ManyToOne()
    private Property property;

    public Room(String roomName, Double roomWidth, Double roomLength) {
        this.roomName = roomName;
        this.roomWidth = roomWidth;
        this.roomLength = roomLength;
    }

    public Double getArea() {
        return this.roomWidth * this.roomLength;
    }
}
