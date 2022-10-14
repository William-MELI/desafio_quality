package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PropertyRequest {

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "/([A-Z]|[0-9])[\\\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$/") //First capital letter
    private String propName;

    @NotNull
    @JsonProperty("district")
    private DistrictRequest districtRequest;

    @NotEmpty
    @JsonProperty("roomList")
    private List<RoomRequest> roomRequestList = new ArrayList<>();

    public Property toEntity() {
        List<Room> roomList = new ArrayList<>();
        roomRequestList.forEach(l -> roomList.add(l.toEntity()));

        Property property = new Property(propName, districtRequest.toEntity(), roomList);
        return property;
    }
}
