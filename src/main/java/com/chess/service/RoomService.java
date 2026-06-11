package com.chess.service;
import com.chess.entity.Room;
import com.chess.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoomService {
    @Autowired
    private RoomMapper roomMapper;
    public List<Room> getAllRooms() { return roomMapper.selectList(null); }
    public List<Room> getAvailableRooms() { return roomMapper.selectAvailableRooms(); }
}