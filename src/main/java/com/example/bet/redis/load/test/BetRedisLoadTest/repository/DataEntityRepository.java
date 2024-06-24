package com.example.bet.redis.load.test.BetRedisLoadTest.repository;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {
    @Query("SELECT d.data FROM DataEntity d WHERE d.id = :id")
    String findDataById(Long id);
}
