package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {

    private String roomName;
    private Double roomWidth;
    private Double roomLength;

    public Room toEntity() {
        return new Room(roomName, roomWidth, roomLength);
    }
}
