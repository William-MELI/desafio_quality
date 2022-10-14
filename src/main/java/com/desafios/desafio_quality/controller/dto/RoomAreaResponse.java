package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAreaResponse that = (RoomAreaResponse) o;
        return Objects.equals(roomName, that.roomName) && Objects.equals(roomLenght, that.roomLenght) && Objects.equals(roomWidth, that.roomWidth) && Objects.equals(totalArea, that.totalArea);
    }

}
