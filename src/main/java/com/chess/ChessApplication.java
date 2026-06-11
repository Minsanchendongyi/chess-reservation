package com.chess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chess.mapper")
public class ChessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChessApplication.class, args);
        System.out.println("✅ 棋牌室预约系统启动成功！");
    }
}