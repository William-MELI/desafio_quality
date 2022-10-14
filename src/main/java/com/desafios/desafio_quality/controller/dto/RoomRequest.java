package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Room;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Getter
@Setter
public class RoomRequest {

    @NotBlank(message = "O nome do cômodo não pode estar em branco.")
    @Size(max = 30, message = "O nome do cômodo deve ter no máximo 30 caracteres.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome do cômodo deve começar com letra maiúscula.")
    private String roomName;

    @Positive(message = "A largura do cômodo deve ser um número positivo.")
    @Range(max = 25, message = "A largura do cômodo deve ser no máximo 25m.")
    private Double roomWidth;

    @Positive(message = "O comprimento do cômodo deve ser um número positivo.")
    @Range(max = 33, message = "O comprimento do cômodo deve ser no máximo 33m.")
    private Double roomLength;

    public Room toEntity() {
        return new Room(roomName, roomWidth, roomLength);
    }
}
