package com.example.bet.redis.load.test.BetRedisLoadTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPooled;

import java.time.Duration;

@org.springframework.context.annotation.Configuration
public class Configuration {
    //@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    //@Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public JedisPooled jedisPooled(){
        ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
// maximum active connections in the pool,
// tune this according to your needs and application type
// default is 8
        poolConfig.setMaxTotal(16);

// maximum idle connections in the pool, default is 8
        poolConfig.setMaxIdle(16);
// minimum idle connections in the pool, default 0
        poolConfig.setMinIdle(0);

// Enables waiting for a connection to become available.
        poolConfig.setBlockWhenExhausted(true);
// The maximum number of seconds to wait for a connection to become available
        poolConfig.setMaxWait(Duration.ofSeconds(1));

// Enables sending a PING command periodically while the connection is idle.
        poolConfig.setTestWhileIdle(true);
// controls the period between checks for idle connections in the pool
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(1));

// JedisPooled does all hard work on fetching and releasing connection to the pool
// to prevent connection starvation
        return new JedisPooled(poolConfig, "localhost", 6379);
    }
}
