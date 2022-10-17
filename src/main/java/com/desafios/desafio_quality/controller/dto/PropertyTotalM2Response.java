package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * This is used as response in endpoint to send Property and its squared area
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyTotalM2Response {
    /**
     * Property name
     */
    private String propName;
    /**
     * Property District reference
     */
    private DistrictResponse district;
    /**
     * List of Room related to the Property
     */
    private List<RoomResponse> roomList;
    /**
     * Property total squared area
     */
    private Double totalM2;

    public static PropertyTotalM2Response toResponse(Property property) {
        return PropertyTotalM2Response.builder()
                .propName(property.getPropName())
                .district(DistrictResponse.toResponse(property.getDistrict()))
                .roomList(RoomResponse.toResponse(property.getRoomList()))
                .build();
    }
}
