package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.exception.NoRoomFoundInPropertyException;
import com.desafios.desafio_quality.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomAreaResponse> getAllRoomAreasByPropertyId(Long propertyId) {
        Optional<List<Room>> roomList = Optional.ofNullable(roomRepository.findByPropertyId(propertyId));

        if (roomList.get().isEmpty()) {
            throw new NoRoomFoundInPropertyException(String.format("Nenhum cômodo encontrado na propriedade com ID %d", propertyId));
        } else {
            return roomList.get().stream().map(r -> new RoomAreaResponse(r)).toList();
        }
    }

    public Optional<RoomAreaResponse> findBiggerRoom(Long id) throws NoRoomFoundInPropertyException {
          List<RoomAreaResponse> listRoom = getAllRoomAreasByPropertyId(id);
        if (listRoom.isEmpty()) {
            throw new NoRoomFoundInPropertyException(String.format("Nenhum cômodo encontrado na propriedade com ID %d", id));
        } else {
            return listRoom.stream()
                    .max(Comparator.comparing(RoomAreaResponse::getTotalArea));
        }
    }
}
