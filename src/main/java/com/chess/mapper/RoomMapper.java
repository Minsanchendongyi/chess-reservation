package com.chess.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chess.entity.Room;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface RoomMapper extends BaseMapper<Room> {
    @Select("SELECT * FROM room WHERE status = '空闲'")
    List<Room> selectAvailableRooms();
}