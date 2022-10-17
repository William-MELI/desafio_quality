package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.entity.District;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.exception.NoRoomFoundInPropertyException;
import com.desafios.desafio_quality.service.PropertyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyService propertyService;

    @BeforeEach
    void setup() {

    }

    @Test
    void getAllAreas_returnRoomAreaResponseList_whenSuccess() throws Exception {
        List<Room> roomList = new ArrayList<>();
        Property newProperty = new Property();
        District newDistrict = new District();

        newDistrict.setValueDistrictM2(BigDecimal.valueOf(10));
        newDistrict.setPropDistrict("New District");

        roomList.add(new Room("Bath", 10.0, 20.0));
        roomList.add(new Room("Bedroom", 20.0, 10.0));
        roomList.add(new Room("Kitchen", 15.0, 15.0));

        newProperty.setPropName("New Property");
        newProperty.setRoomList(roomList);
        newProperty.setDistrict(newDistrict);

        Property savedProperty = propertyService.save(newProperty);

        ResultActions response = mockMvc.perform(
                get("/room/{propertyId}", savedProperty.getId()).contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[*].roomName", containsInAnyOrder(roomList.stream().map(Room::getRoomName).toArray())))
                .andExpect(jsonPath("$[*].roomLenght", containsInAnyOrder(roomList.stream().map(Room::getRoomLength).toArray())))
                .andExpect(jsonPath("$[*].roomWidth", containsInAnyOrder(roomList.stream().map(Room::getRoomWidth).toArray())))
                .andExpect(jsonPath("$[*].totalArea", containsInAnyOrder(roomList.stream().map(Room::getArea).toArray())));
    }

//    @Test
//    void getAllAreas_throwNoRoomFoundInPropertyException_whenInexistentProperty() throws Exception {
//
//        mockMvc.perform(
//                get("/room/{propertyId}", Long.MAX_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON));
//                .andExpect(status().isInternalServerError())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoRoomFoundInPropertyException))
//                .andReturn();
//    }

    @Test
    void getAllAreas_throwNoRoomFoundInPropertyException_whenInexistentProperty() throws Exception {

        ResultActions response =mockMvc.perform(get("/room/{propertyId}", Long.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(
                        result -> assertTrue(
                                result.getResolvedException() instanceof NoRoomFoundInPropertyException));
    }
}