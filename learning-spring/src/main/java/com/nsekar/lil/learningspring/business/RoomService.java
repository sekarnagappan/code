package com.nsekar.lil.learningspring.business;

import com.nsekar.lil.learningspring.data.Room;
import com.nsekar.lil.learningspring.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomView> getRoomsList(){
        List<RoomView> roomsList = new ArrayList<RoomView>();
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(room -> {
            RoomView roomView = new RoomView();
            roomView.setRoomId(room.getId());
            roomView.setRoomName(room.getName());
            roomView.setRoomNumber(room.getRoomNumber());
            roomView.setBedInfo(room.getBedInfo());

            roomsList.add(roomView);
        });
        return roomsList;
    }
}
