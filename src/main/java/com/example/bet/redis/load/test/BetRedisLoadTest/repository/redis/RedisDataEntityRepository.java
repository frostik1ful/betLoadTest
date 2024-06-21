package com.example.bet.redis.load.test.BetRedisLoadTest.repository.redis;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis.RedisDataEntity;
import org.springframework.data.repository.CrudRepository;

public interface RedisDataEntityRepository extends CrudRepository<RedisDataEntity, String> {
}
