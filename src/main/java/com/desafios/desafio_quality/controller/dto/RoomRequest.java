package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RoomRequest {

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "/([A-Z]|[0-9])[\\\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$/") //First capital letter
    private String roomName;

    @Positive
    @Max(25)
    private Double roomWidth;

    @Positive
    @Max(33)
    private Double roomLength;

    public Room toEntity() {
        return new Room(roomName, roomWidth, roomLength);
    }
}
