package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    RoomService roomService;

    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void setup() {

    }

    @Test
    @DisplayName("Calculate area in list with multiple rooms")
    void getAllRoomAreasByPropertyId_returnsListWithRoomDTO_whenSuccess() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Bath", 10.0, 20.0));
        roomList.add(new Room("Bedroom", 20.0, 10.0));
        roomList.add(new Room("Kitchen", 15.0, 15.0));

        List<RoomAreaResponse> roomAreaResponses = new ArrayList<>();
        roomAreaResponses.add(new RoomAreaResponse(roomList.get(0)));
        roomAreaResponses.add(new RoomAreaResponse(roomList.get(1)));
        roomAreaResponses.add(new RoomAreaResponse(roomList.get(2)));

        Mockito.when(roomRepository.findByProperty(ArgumentMatchers.anyLong())).thenReturn(roomList);

        List<RoomAreaResponse> responseRoomAreaList = this.roomService.getAllRoomAreasByPropertyId(ArgumentMatchers.anyLong());

//        responseRoomAreaList.equals(roomAreaResponses);

        assertTrue(
                responseRoomAreaList.size() == roomAreaResponses.size() &&
                responseRoomAreaList.containsAll(roomAreaResponses) &&
                roomAreaResponses.containsAll(responseRoomAreaList)
        );

    }

}