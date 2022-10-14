package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyTotalM2Response {
    private String propName;
    private DistrictResponse district;
    private List<RoomResponse> roomList;
    private Double totalM2;

    public static PropertyTotalM2Response toResponse(Property property) {
        return PropertyTotalM2Response.builder()
                .propName(property.getPropName())
                .district(DistrictResponse.toResponse(property.getDistrict()))
                .roomList(RoomResponse.toResponse(property.getRoomList()))
                .build();
    }
}
