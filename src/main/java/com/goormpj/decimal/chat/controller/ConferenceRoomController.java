package com.goormpj.decimal.chat.controller;

import com.goormpj.decimal.chat.model.ConferenceRoom;
import com.goormpj.decimal.chat.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conferenceRooms")
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @PostMapping
    public ConferenceRoom createConferenceRoom(@RequestBody ConferenceRoom conferenceRoom) {
        return conferenceRoomService.createConferenceRoom(conferenceRoom);
    }

    @GetMapping
    public List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomService.getAllConferenceRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceRoom> getConferenceRoomById(@PathVariable Long id) {
        return conferenceRoomService.getConferenceRoomById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
