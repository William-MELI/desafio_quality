package com.desafios.desafio_quality.repository;

<<<<<<< HEAD
import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
=======
import com.desafios.desafio_quality.entity.Property;
>>>>>>> master
import com.desafios.desafio_quality.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByPropertyId(Long propertyId);
    List<Room> findByProperty(Property property);
}
