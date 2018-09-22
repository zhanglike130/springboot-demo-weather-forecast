package com.zhang.repository;

import com.zhang.entity.TodayWeather;
import org.springframework.data.mongodb.repository.MongoRepository;


//@NoRepositoryBean
//@Repository用于标注数据访问组件，即 DAO组件

public interface TodayWeatherRepository extends MongoRepository<TodayWeather,String> {
   TodayWeather findByCityDate(String cityDate);
   void deleteByCityDate(String cityDate);
}
