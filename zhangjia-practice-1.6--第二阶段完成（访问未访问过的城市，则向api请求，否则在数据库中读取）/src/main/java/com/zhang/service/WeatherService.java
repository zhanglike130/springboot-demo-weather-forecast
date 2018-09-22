package com.zhang.service;

import com.zhang.entity.Weather;
import com.zhang.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //获取 weather
    public List<Weather> getWeatherList(String cityDate)
    {
        return weatherRepository.findAllByCityDate(cityDate);
    }

    //增加 weather
    public void addWeatherList( List<Weather>  weathers)
    {
        for ( Weather w : weathers )
           weatherRepository.save(w);
    }

    //删除
    public void deleteWeatherList(String cityAndDate)
    {
        weatherRepository.deleteAllByCityDate(cityAndDate);
    }
}
