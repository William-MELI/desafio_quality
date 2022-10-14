package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomAreaResponse {

    String roomName;
    Double roomLenght;
    Double roomWidth;
    Double totalArea;

    public RoomAreaResponse(Room room){
        this.totalArea = room.getArea();
        this.roomLenght = room.getRoomLength();
        this.roomWidth = room.getRoomWidth();
        this.roomName = room.getRoomName();
    }

}
