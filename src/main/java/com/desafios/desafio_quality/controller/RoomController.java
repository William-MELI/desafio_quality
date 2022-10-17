package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
import com.desafios.desafio_quality.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * All endpoints related to Room
 * It is a Spring @RestController
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint to return all the rooms and it respective area
     * Returns 200 OK in case operation is success.
     * @param id the property ID
     * @return a list with rooms and it respective area
     */
    @GetMapping("/{id}")
    ResponseEntity<List<RoomAreaResponse>> getAllAreas(@PathVariable Long id) {
        return ResponseEntity.ok(this.roomService.getAllRoomAreasByPropertyId(id));
    }

    /**
     * Endpoint to return the biggest room in a Property reference
     * Returns 200 OK in case operation is success.
     * @param id the property ID
     * @return the biggest room in a property
     */
    @GetMapping("/filter-bigger-room")
    Optional<RoomAreaResponse> findBiggerRoom(@RequestParam Long id) {
        return roomService.findBiggerRoom(id);
    }
}