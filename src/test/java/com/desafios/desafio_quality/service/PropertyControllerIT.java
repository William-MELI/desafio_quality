package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.entity.District;
import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
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

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerIT {

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
}
