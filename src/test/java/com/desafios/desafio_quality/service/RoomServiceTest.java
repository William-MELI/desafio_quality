package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.entity.Property;
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

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    RoomService roomService;

    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void setup(){

    }

    @Test
    @DisplayName("Calculate area in list with multiple rooms")
    void getAllRoomAreasByPropertyId_returnsListWithRoomDTO_whenSuccess(){
        List<Room> roomList = new ArrayList<Room>();
        Property prop = new Property();
        roomList.add(new Room(1L,prop,"Bath", 10.0,20.0));
        roomList.add(new Room(2L,prop,"Bedroom", 20.0,10.0));
        roomList.add(new Room(3L,prop,"Kitchen", 15.0,15.0));

        Mockito.when(roomRepository.findByProperty(ArgumentMatchers.anyLong())).thenReturn(roomList);

        List<Room> listRoom = this.roomService.getAllRoomAreasByPropertyId(ArgumentMatchers.anyLong());

        System.out.println(listRoom);

    }

}