package com.desafios.desafio_quality.controller;

import com.desafios.desafio_quality.controller.dto.RoomAreaResponse;
import com.desafios.desafio_quality.entity.Room;
import com.desafios.desafio_quality.repository.RoomRepository;
import com.desafios.desafio_quality.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    ResponseEntity<List<RoomAreaResponse>> getAllAreas(@PathVariable Long id) {
        return ResponseEntity.ok(this.roomService.getAllRoomAreasByPropertyId(id));
    }

}