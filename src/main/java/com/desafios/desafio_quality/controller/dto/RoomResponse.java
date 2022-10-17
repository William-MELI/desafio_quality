package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is used as response for Room related endpoints
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {
    /**
     * Room id
     */
    private Long id;
    /**
     * Room name
     */
    private String roomName;
    /**
     * Room width
     */
    private Double roomWidth;
    /**
     * Room length
     */
    private Double roomLength;

    public static List<RoomResponse> toResponse(List<Room> roomList) {
        List<RoomResponse> roomResponseList = new ArrayList<>();

        roomList.forEach(r -> roomResponseList.add(RoomResponse.builder()
                        .id(r.getId())
                        .roomName(r.getRoomName())
                        .roomWidth(r.getRoomWidth())
                        .roomLength(r.getRoomLength())
                        .build()));

        return roomResponseList;
    }
}
