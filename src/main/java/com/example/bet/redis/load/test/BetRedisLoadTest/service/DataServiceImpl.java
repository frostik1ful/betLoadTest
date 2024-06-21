package com.example.bet.redis.load.test.BetRedisLoadTest.service;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis.RedisDataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.mapper.RedisMapper;
import com.example.bet.redis.load.test.BetRedisLoadTest.repository.DataEntityRepository;
import com.example.bet.redis.load.test.BetRedisLoadTest.repository.redis.RedisDataEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService{
    private final DataEntityRepository dataEntityRepository;
    private final RedisDataEntityRepository redisDataEntityRepository;

    @Override
    public void addDefaultData() {
        if (dataEntityRepository.count() == 0){
            String file = "src/main/resources/test.json";
            String json = null;
            try {
                json = new String(Files.readAllBytes(Paths.get(file)));
                DataEntity dataEntity = new DataEntity();
                dataEntity.setData(json);
                log.info("stating add 37000 rows to table");
                for (long i = 1; i <= 37000; i++) {
                    dataEntity.setId(i);
                    dataEntityRepository.save(dataEntity);
                    //dataEntityRepository.saveJson(i, json);
                }
                log.info("37000 rows added to table!");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            /*Optional<DataEntity> optionalDataEntity = dataEntityRepository.findById(1L);
            if (optionalDataEntity.isPresent()){
                DataEntity dataEntity = optionalDataEntity.get();
                for (long i = 2; i < 12; i++) {
                    dataEntity.setId(i);
                    dataEntityRepository.save(dataEntity);
                }
            }*/
        }
    }

    @Override
    public RedisDataEntity findRedisById(String id) {
        return redisDataEntityRepository.findById(id).orElse(null);
    }

    @Override
    public DataEntity findPostgreById(Long id) {
        return dataEntityRepository.findById(id).orElse(null);
    }

    @Override
    public RedisDataEntity redisResponseOrCreateIfEmpty(String id) {
        Optional<RedisDataEntity> optionalRedisDataEntity = redisDataEntityRepository.findById(id);
        if (optionalRedisDataEntity.isEmpty()){
            Optional<DataEntity> optionalDataEntity = dataEntityRepository.findById(Long.valueOf(id));
            if (optionalDataEntity.isPresent()){
                DataEntity dataEntity = optionalDataEntity.get();
                RedisDataEntity redisDataEntity = RedisMapper.mapDataEntityToRedisDataEntity(dataEntity);
                redisDataEntityRepository.save(redisDataEntity);
                return redisDataEntity;
            }
        }
        else {
            return optionalRedisDataEntity.get();
        }
        return null;
    }
}
