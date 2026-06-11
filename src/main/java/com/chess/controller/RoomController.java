package com.chess.controller;
import com.chess.common.Result;
import com.chess.entity.Room;
import com.chess.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping
    public Result<List<Room>> getAllRooms() { return Result.success(roomService.getAllRooms()); }
    @GetMapping("/available")
    public Result<List<Room>> getAvailableRooms() { return Result.success(roomService.getAvailableRooms()); }
}