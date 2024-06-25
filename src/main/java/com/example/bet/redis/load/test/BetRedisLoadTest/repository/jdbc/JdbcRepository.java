package com.example.bet.redis.load.test.BetRedisLoadTest.repository.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class JdbcRepository {
    @Value("${spring.datasource.url}")
    private  String url;
    @Value("${spring.datasource.username}")
    private  String username;
    @Value("${spring.datasource.password}")
    private  String password;
    //private Connection connection;

    private  HikariDataSource hikariDataSource;

    @PostConstruct
    public void setUp(){
        HikariConfig config = new HikariConfig();
        hikariDataSource = new HikariDataSource();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(30);
        config.setDriverClassName("org.postgresql.Driver");
        //config.addDataSourceProperty("cachePrepStmts", "true");
        //config.addDataSourceProperty("prepStmtCacheSize", "250");
        //config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(config);

        /*try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public String findById(long id){
        String result = null;

        try (PreparedStatement preparedStatement = hikariDataSource.getConnection().prepareStatement("SELECT d.data FROM data_entityes d WHERE d.id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
