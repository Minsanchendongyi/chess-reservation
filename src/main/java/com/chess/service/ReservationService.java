package com.chess.service;
import com.chess.entity.Reservation;
import com.chess.entity.Room;
import com.chess.mapper.ReservationMapper;
import com.chess.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private RoomMapper roomMapper;
    public boolean checkConflict(Integer roomId, LocalDateTime startTime, LocalDateTime endTime) {
        int count = reservationMapper.checkConflict(roomId,
                startTime.toString().replace("T", " "),
                endTime.toString().replace("T", " "));
        return count > 0;
    }
    @Transactional
    public boolean createReservation(Integer userId, Integer roomId,
                                     LocalDateTime startTime, LocalDateTime endTime) {
        if (checkConflict(roomId, startTime, endTime)) return false;
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setStatus("已预约");
        reservation.setCreateTime(LocalDateTime.now());
        Room room = roomMapper.selectById(roomId);
        room.setStatus("已预约");
        roomMapper.updateById(room);
        return reservationMapper.insert(reservation) > 0;
    }
    public List<Reservation> getUserReservations(Integer userId) {
        return reservationMapper.selectByUserId(userId);
    }
    @Transactional
    public boolean cancelReservation(Integer id, Integer userId) {
        Reservation reservation = reservationMapper.selectById(id);
        if (reservation == null || !reservation.getUserId().equals(userId)) return false;
        if (!"已预约".equals(reservation.getStatus())) return false;
        reservation.setStatus("已取消");
        reservationMapper.updateById(reservation);
        Room room = roomMapper.selectById(reservation.getRoomId());
        room.setStatus("空闲");
        roomMapper.updateById(room);
        return true;
    }
}