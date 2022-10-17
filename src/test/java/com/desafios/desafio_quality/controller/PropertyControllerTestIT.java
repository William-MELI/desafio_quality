package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.DistrictRequest;
import com.desafios.desafio_quality.controller.dto.PropertyRequest;
import com.desafios.desafio_quality.controller.dto.RoomRequest;
import com.desafios.desafio_quality.entity.District;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.exception.NoRoomFoundInPropertyException;

import com.desafios.desafio_quality.exception.PropertyNotFoundException;
import com.desafios.desafio_quality.service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {

    }

    @Test
    void testCreate() throws Exception {

        List<RoomRequest> roomRequestList = new ArrayList<>();
        roomRequestList.add(RoomRequest.builder()
                        .roomName("Bedroom")
                        .roomWidth(5.0)
                        .roomLength(2.60)
                .build());
        roomRequestList.add(RoomRequest.builder()
                .roomName("Kitchen")
                .roomWidth(7.9)
                .roomLength(3.75)
                .build());
        roomRequestList.add(RoomRequest.builder()
                .roomName("Bathroom")
                .roomWidth(3.85)
                .roomLength(3.0)
                .build());

        PropertyRequest propertyRequest = PropertyRequest.builder()
                .propName("Property Test")
                .districtRequest(DistrictRequest.builder()
                        .propDistrict("District Test")
                        .valueDistrictM2(new BigDecimal(97))
                        .build())
                .roomRequestList(roomRequestList)
                .build();

        ResultActions resposta = mockMvc.perform(
                post("/property")
                        .content(objectMapper.writeValueAsString(propertyRequest))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated());

    }

    @Test
    void testFindById() throws Exception {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Bedroom", 5.00, 2.60));
        roomList.add(new Room("Kitchen", 7.90, 3.75));
        roomList.add(new Room("Bathroom", 3.85, 3.00));

        Property property = new Property(1L, "Property Test",
                new District(1L, "District Test", new BigDecimal(97.00)),
                roomList);

        propertyService.save(property);

        ResultActions resposta = mockMvc.perform(
                get("/property")
                        .param("id", String.valueOf(property.getId()))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.propName", CoreMatchers.is(property.getPropName())))
                .andExpect(jsonPath("$.district.id", CoreMatchers.equalTo(property.getDistrict().getId().intValue())))
                .andExpect(jsonPath("$.district.propDistrict", CoreMatchers.is(property.getDistrict().getPropDistrict())))
                .andExpect(jsonPath("$.district.valueDistrictM2", CoreMatchers.is(property.getDistrict().getValueDistrictM2().doubleValue())))
                .andExpect(jsonPath("$..roomList[*].roomName", CoreMatchers.is(roomList.stream().map(Room::getRoomName).toList())))
                .andExpect(jsonPath("$..roomList[*].roomWidth", CoreMatchers.is(roomList.stream().map(Room::getRoomWidth).toList())))
                .andExpect(jsonPath("$..roomList[*].roomLength", CoreMatchers.is(roomList.stream().map(Room::getRoomLength).toList())));
    }

    @Test
    void getTotalM2PropertyById_returnPropertyTotalM2Response_whenSucess() throws Exception {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Bedroom", 5.00, 2.60));
        roomList.add(new Room("Kitchen", 7.90, 3.75));
        roomList.add(new Room("Bathroom", 3.85, 3.00));

        Property property = new Property(1L, "Property Test",
                new District(1L, "District Test", new BigDecimal(97.00)),
                roomList);

        propertyService.save(property);

        Double totalM2Expected = propertyService.getTotalM2PropertyById(property.getId());

        ResultActions resposta = mockMvc.perform(
                get("/property/totalM2")
                        .param("id", String.valueOf(property.getId()))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalM2", CoreMatchers.is(totalM2Expected)))
                .andExpect(jsonPath("$.propName", CoreMatchers.is(property.getPropName())));

    }

    @Test
    void getPricePropertyById_returnPropertyTotalValueResponse_whenSucess() throws Exception {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Bedroom", 5.00, 2.60));
        roomList.add(new Room("Kitchen", 7.90, 3.75));
        roomList.add(new Room("Bathroom", 3.85, 3.00));

        Property property = new Property(1L, "Property Test",
                new District(1L, "District Test", new BigDecimal(97)),
                roomList);

        propertyService.save(property);

        BigDecimal totalPriceExpected = propertyService.pricePropertyById(property.getId());

        ResultActions resposta = mockMvc.perform(
                get("/property/prop-price")
                        .param("id", String.valueOf(property.getId()))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.propTotalPrice", CoreMatchers.is(totalPriceExpected)))
                .andExpect(jsonPath("$.propName", CoreMatchers.is(property.getPropName())))
                .andExpect(jsonPath("$.valueDistrictM2", CoreMatchers.is(property.getDistrict().getValueDistrictM2().doubleValue())));
    }

    @Test
    void getPropertyPriceById_throwPropertyNotFoundException_whenPassWrongId() throws Exception {

        ResultActions response = mockMvc.perform(get("/property/prop-price")
                .param("id", String.valueOf(Long.MAX_VALUE))
                .contentType(MediaType.APPLICATION_JSON));
        
        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof PropertyNotFoundException));
    }
                                result.getResolvedException() instanceof PropertyNotFoundException));
    }

}
    void getTotalM2PropertyById_throwPropertyNotFoundException_whenInexistentProperty() throws Exception {

        ResultActions response = mockMvc.perform(get("/property/totalM2")
                .param("id", String.valueOf(Long.MAX_VALUE))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof PropertyNotFoundException));
    }
                                result.getResolvedException() instanceof PropertyNotFoundException));
    }

}
