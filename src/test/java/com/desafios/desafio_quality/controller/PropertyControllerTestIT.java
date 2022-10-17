package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.entity.District;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.exception.PropertyNotFoundException;
import com.desafios.desafio_quality.service.PropertyService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @BeforeEach
    void setup() {

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
                .andExpect(jsonPath("$.propTatalPrice", CoreMatchers.is(totalPriceExpected)))
                .andExpect(jsonPath("$.propName", CoreMatchers.is(property.getPropName())))
                .andExpect(jsonPath("$.valueDistrictM2", CoreMatchers.is(property.getDistrict().getValueDistrictM2().doubleValue())));
    }

    @Test
    void getTotalM2PropertyById_throwPropertyNotFoundException_whenInexistentProperty() throws Exception {

        ResultActions response = mockMvc.perform(get("/property/totalM2")
                .param("id", String.valueOf(Long.MAX_VALUE))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                                result.getResolvedException() instanceof PropertyNotFoundException));
    }

}
