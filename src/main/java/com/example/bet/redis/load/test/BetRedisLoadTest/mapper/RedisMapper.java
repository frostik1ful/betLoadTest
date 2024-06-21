package com.example.bet.redis.load.test.BetRedisLoadTest.mapper;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis.RedisDataEntity;

public interface RedisMapper {
    static RedisDataEntity mapDataEntityToRedisDataEntity(DataEntity data) {
        return new RedisDataEntity(String.valueOf(data.getId()), data.getData());
    }

    static DataEntity mapDataEntityToRedisDataEntity(RedisDataEntity data) {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setId(Long.valueOf(data.getId()));
        dataEntity.setData(data.getData());
        return dataEntity;
    }
}
