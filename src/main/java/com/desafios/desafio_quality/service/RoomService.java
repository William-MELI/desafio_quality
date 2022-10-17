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

/**
 * This class contains all Room related functions
 * It is a Spring @Service
 */
@Service
public class RoomService implements IRoomService {
    RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Returns all Rooms and its area given Property ID
     * @param propertyId the Property ID
     * @return a list with all all Rooms and respective Area
     */
    public List<RoomAreaResponse> getAllRoomAreasByPropertyId(Long propertyId) {
        Optional<List<Room>> roomList = Optional.ofNullable(roomRepository.findByPropertyId(propertyId));

        if (roomList.get().isEmpty()) {
            throw new NoRoomFoundInPropertyException(String.format("Nenhum cômodo encontrado na propriedade com ID %d", propertyId));
        } else {
            return roomList.get().stream().map(r -> new RoomAreaResponse(r)).toList();
        }
    }

    /**
     * Returns the biggest(using the Area as criteria) room given a Property ID.
     * @param id the Property ID
     * @return the biggest room found
     */
    public Optional<RoomAreaResponse> findBiggerRoom(Long id) {
        List<RoomAreaResponse> listRoom = getAllRoomAreasByPropertyId(id);
        return listRoom.stream()
                .max(Comparator.comparing(RoomAreaResponse::getTotalArea));
    }
}
