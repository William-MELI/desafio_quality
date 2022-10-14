package com.desafios.desafio_quality.repository;

import com.desafios.desafio_quality.entity.Property;
import com.desafios.desafio_quality.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByPropertyId(Long propertyId);
    List<Room> findByProperty(Property property);
}
