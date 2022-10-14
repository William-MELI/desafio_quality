package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;

import java.util.List;
import java.util.Optional;

public interface IRoomService {

    List<RoomAreaResponse> getAllRoomAreasByPropertyId(Long propertyId);

    Optional<RoomAreaResponse> findBiggerRoom(Long id);
}
