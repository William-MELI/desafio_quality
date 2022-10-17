package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.*;

import java.util.Objects;

/**
 * This is used in endpoint to send Room and it respective Squared area.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomAreaResponse {

    /**
     * Room name
     */
    String roomName;
    /**
     * Room length
     */
    Double roomLenght;
    /**
     * Room width
     */
    Double roomWidth;
    /**
     * Room squared area
     */
    Double totalArea;

    public static RoomAreaResponse toResponse(Room room) {
        return RoomAreaResponse.builder()
                .totalArea(room.getArea())
                .roomWidth(room.getRoomWidth())
                .roomLenght(room.getRoomLength())
                .roomName(room.getRoomName())
                .build();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAreaResponse that = (RoomAreaResponse) o;
        return Objects.equals(roomName, that.roomName) && Objects.equals(roomLenght, that.roomLenght) && Objects.equals(roomWidth, that.roomWidth) && Objects.equals(totalArea, that.totalArea);
    }

}
