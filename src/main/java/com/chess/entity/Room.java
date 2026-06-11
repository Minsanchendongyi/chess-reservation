package com.chess.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("room")
public class Room {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer price;
    private String status;
    private String type;
}