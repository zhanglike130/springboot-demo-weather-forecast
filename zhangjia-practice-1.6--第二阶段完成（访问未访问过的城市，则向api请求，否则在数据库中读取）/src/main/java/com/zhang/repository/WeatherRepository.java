package com.zhang.repository;

import com.zhang.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeatherRepository  extends MongoRepository<Weather,String> {
     List<Weather> findAllByCityDate(String cityDate);
     void deleteAllByCityDate(String cityDate);
}