package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
import com.desafios.desafio_quality.entity.Room;

import java.util.List;

public interface IRoomService {

    List<RoomAreaResponse> getAllRoomAreasByPropertyId(Long propertyId);
}
