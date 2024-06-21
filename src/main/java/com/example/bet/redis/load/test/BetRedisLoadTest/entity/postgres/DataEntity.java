package com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "data_entityes")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String data;
}
