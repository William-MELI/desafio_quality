package com.desafios.desafio_quality.service;

import com.desafios.desafio_quality.entity.Room;

import java.util.List;

public interface IRoomService {

    List<Room> getAllRoomAreasByPropertyId(Long propertyId);
}
