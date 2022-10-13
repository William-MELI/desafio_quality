package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PropertyRequest {

    private String propName;

    @JsonProperty("district")
    private DistrictRequest districtRequest;

    @JsonProperty("roomList")
    private List<RoomRequest> roomRequestList = new ArrayList<>();

    public Property toEntity() {
        List<Room> roomList = new ArrayList<>();
        roomRequestList.forEach(l -> roomList.add(l.toEntity()));

        Property property = new Property(propName, districtRequest.toEntity(), roomList);
        return property;
    }
}
