package com.example.bet.redis.load.test.BetRedisLoadTest.repository;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO data_entityes VALUES (:id, CAST(:json AS json))", nativeQuery = true)
    void saveJson(long id, String json);
}
