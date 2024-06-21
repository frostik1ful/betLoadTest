package com.example.bet.redis.load.test.BetRedisLoadTest.repository;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {
}
