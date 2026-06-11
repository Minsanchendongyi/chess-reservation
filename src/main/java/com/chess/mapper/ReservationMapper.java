package com.chess.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chess.entity.Reservation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface ReservationMapper extends BaseMapper<Reservation> {
    @Select("SELECT * FROM reservation WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Reservation> selectByUserId(@Param("userId") Integer userId);
    @Select("SELECT COUNT(*) FROM reservation WHERE room_id = #{roomId} AND " +
            "((start_time <= #{endTime} AND end_time > #{startTime}) OR " +
            "(start_time < #{endTime} AND end_time >= #{startTime}))")
    int checkConflict(@Param("roomId") Integer roomId,
                      @Param("startTime") String startTime,
                      @Param("endTime") String endTime);
}