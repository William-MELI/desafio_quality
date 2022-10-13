package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    private Long id;
    private String propName;
    private DistrictResponse district;
    private List<RoomResponse> roomList;

    public static PropertyResponse toResponse(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .propName(property.getPropName())
                .district(DistrictResponse.toResponse(property.getDistrict()))
                .roomList(RoomResponse.toResponse(property.getRoomList()))
                .build();
    }

}
