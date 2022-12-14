package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is used as entry for the Property related endpoints.
 * Contains all required validations as specified by the exercise.
 *
 */
@Builder
@Getter
public class PropertyRequest {

    /**
     * Property name
     */
    @NotBlank(message = "O nome do imóvel não pode estar em branco.")
    @Size(max = 30, message = "O nome do ímovel deve ter no máximo 30 caracteres.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome do imóvel deve começar com letra maiúscula.")
    private String propName;

    /**
     * Property District reference
     */
    @NotNull(message = "O bairro não pode ser nulo.")
    @JsonProperty("district")
    @Valid
    private DistrictRequest districtRequest;

    /**
     * List of Room related to the Property
     */
    @NotEmpty(message = "O imóvel deve ter no mínimo um cômodo.")
    @JsonProperty("roomList")
    @Valid
    private List<RoomRequest> roomRequestList = new ArrayList<>();

    public Property toEntity() {
        List<Room> roomList = new ArrayList<>();
        roomRequestList.forEach(l -> roomList.add(l.toEntity()));

        Property property = new Property(propName, districtRequest.toEntity(), roomList);
        return property;
    }
}
