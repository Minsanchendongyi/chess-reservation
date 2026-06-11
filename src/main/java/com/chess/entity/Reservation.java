package com.chess.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@TableName("reservation")
public class Reservation {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private LocalDateTime createTime;
}