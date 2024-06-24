package com.example.bet.redis.load.test.BetRedisLoadTest.service;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis.RedisDataEntity;

public interface DataService {

    void addDefaultData();

    RedisDataEntity findRedisById(String id);

    String findPostgreById(Long id);

    RedisDataEntity redisResponseOrCreateIfEmpty(String id);

    String findRedisPooledById(String id);

    String findJDBCById(Long id);
}
