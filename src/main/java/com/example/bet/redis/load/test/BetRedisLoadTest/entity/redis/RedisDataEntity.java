package com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

//@RedisHash("DataEntity")
@AllArgsConstructor
@Data
public class RedisDataEntity {
    private String id;
    private String data;
}
