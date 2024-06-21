package com.example.bet.redis.load.test.BetRedisLoadTest.controller;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis.RedisDataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.service.DataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class LoadController {
    private final DataServiceImpl dataService;

    @GetMapping("/")
    public ResponseEntity<String> simpleResponce(){
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/redis/{id}")
    public ResponseEntity<?> redisResponse(@PathVariable String id){
        RedisDataEntity redisDataEntity = dataService.findRedisById(id);
        if (isNull(redisDataEntity)){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(redisDataEntity);
        }

    }

    @GetMapping("/postgre/{id}")
    public ResponseEntity<?> redisResponse(@PathVariable Long id){
        DataEntity dataEntity = dataService.findPostgreById(id);
        if (isNull(dataEntity)){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(dataEntity);
        }
    }

    @GetMapping("/redis-get-or-create/{id}")
    public ResponseEntity<?> redisResponseOrCreateIfEmpty(@PathVariable String id){
        RedisDataEntity redisDataEntity = dataService.redisResponseOrCreateIfEmpty(id);
        return ResponseEntity.ok(redisDataEntity);
    }
}
