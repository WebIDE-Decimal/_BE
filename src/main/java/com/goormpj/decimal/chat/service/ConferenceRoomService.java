package com.goormpj.decimal.chat.service;

import com.goormpj.decimal.chat.model.ConferenceRoom;
import com.goormpj.decimal.chat.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    public ConferenceRoom createConferenceRoom(ConferenceRoom room) {
        return conferenceRoomRepository.save(room);
    }

    public List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll();
    }

    public Optional<ConferenceRoom> getConferenceRoomById(Long id) {
        return conferenceRoomRepository.findById(id);
    }

}

