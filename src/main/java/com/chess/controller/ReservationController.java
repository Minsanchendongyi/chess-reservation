package com.chess.controller;
import com.chess.common.Result;
import com.chess.entity.Reservation;
import com.chess.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @PostMapping("/create")
    public Result<String> createReservation(@RequestParam Integer roomId,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
                                            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return Result.error("请先登录");
        return reservationService.createReservation(userId, roomId, startTime, endTime) ?
                Result.success("预约成功") : Result.error("该时段已被预约");
    }
    @GetMapping("/my")
    public Result<List<Reservation>> getMyReservations(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return Result.error("请先登录");
        return Result.success(reservationService.getUserReservations(userId));
    }
    @PostMapping("/cancel/{id}")
    public Result<String> cancelReservation(@PathVariable Integer id, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return Result.error("请先登录");
        return reservationService.cancelReservation(id, userId) ?
                Result.success("取消成功") : Result.error("取消失败");
    }
}