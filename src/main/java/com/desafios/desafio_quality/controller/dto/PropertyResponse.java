package com.desafios.desafio_quality.controller.dto;

import com.desafios.desafio_quality.entity.Property;
import lombok.*;

import java.util.List;

/**
 * This is used as response for Property instance.
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    /**
     * Property ID
     */
    private Long id;
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

    public static PropertyResponse toResponse(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .propName(property.getPropName())
                .district(DistrictResponse.toResponse(property.getDistrict()))
                .roomList(RoomResponse.toResponse(property.getRoomList()))
                .build();
    }

}
