package com.desafios.desafio_quality.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * the main Room entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    /**
     * Room ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Property reference.
     * It is an N-1 relationship
     * It will not be sent as response in HTTP requests
     */
    @ManyToOne
    @JsonIgnore
    private Property property;
    /**
     * Room name
     */
    private String roomName;
    /**
     * Room width
     */
    private Double roomWidth;
    /**
     * Room lenght
     */
    private Double roomLength;

    public Room(String roomName, Double roomWidth, Double roomLength) {
        this.roomName = roomName;
        this.roomWidth = roomWidth;
        this.roomLength = roomLength;
    }

    /**
     * Returns the Room squared area
     * @return the room squared area
     */
    public Double getArea() {
        return this.roomWidth * this.roomLength;
    }
}
