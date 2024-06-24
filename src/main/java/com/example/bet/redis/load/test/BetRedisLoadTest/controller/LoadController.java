package com.example.bet.redis.load.test.BetRedisLoadTest.controller;

import com.example.bet.redis.load.test.BetRedisLoadTest.entity.postgres.DataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.entity.redis.RedisDataEntity;
import com.example.bet.redis.load.test.BetRedisLoadTest.service.DataService;
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
    private final DataService dataService;

    @GetMapping("/")
    public ResponseEntity<String> simpleResponse(){
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
    public ResponseEntity<?> postgresResponse(@PathVariable Long id){
        String dataEntity = dataService.findPostgreById(id);
        if (isNull(dataEntity)){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(dataEntity);
        }
    }

    @GetMapping("/redis/pooled/{id}")
    public ResponseEntity<?> redisPooledResponse(@PathVariable String id){
        String json = dataService.findRedisPooledById(id);
        if (isNull(json)){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(json);
        }

    }

    @GetMapping("/jdbc/{id}")
    public ResponseEntity<?> jdbcResponse(@PathVariable Long id){
        String json = dataService.findJDBCById(id);
        if (isNull(json)){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(json);
        }
    }

    @GetMapping("/redis-get-or-create/{id}")
    public ResponseEntity<?> redisResponseOrCreateIfEmpty(@PathVariable String id){
        RedisDataEntity redisDataEntity = dataService.redisResponseOrCreateIfEmpty(id);
        return ResponseEntity.ok(redisDataEntity);
    }
}
