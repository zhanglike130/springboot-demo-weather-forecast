package com.zhang.service;

import com.mongodb.MongoException;
import com.zhang.entity.TodayWeather;
import com.zhang.repository.TodayWeatherRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Service
public class TodayWeatherService {

    @Autowired
    private  TodayWeatherRepository todayWeatherRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //获取
    public TodayWeather getTodayWeather(String cityAndDate)
    {
        TodayWeather weather = new TodayWeather();
        try {
            //连表都没建立.....可以找吗？？
            /*if (todayWeatherRepository == null) {
                return null;
            }*/
            weather = todayWeatherRepository.findByCityDate(cityAndDate);
            if(weather == null)
            {
                System.out.println("weather = = null");
                return null;
            }
           // weather = todayWeatherRepository.findByCityAndDate(city+date);
        }
        catch(MongoException e)
        {
           e.printStackTrace();
        }
        return weather;
    }


    //增加
    public void addTodayWeather(TodayWeather todayWeather)
    {
        try{
            System.out.println("addTodayWeather-----try");
            if(todayWeather != null)
              //todayWeatherRepository.save(new TodayWeather(todayWeather.getCity(),todayWeather.getDate(),todayWeather.getWeek(),todayWeather.getShidu(),todayWeather.getPm25(),todayWeather.getPm10(),todayWeather.getQuality(),todayWeather.getWendu(),todayWeather.getGanmao()));
                todayWeatherRepository.save(todayWeather);
            else
                System.out.println("something error");
        }
        catch(MongoException e)
        {
            System.out.println("addTodayWeather------Exception");
            e.printStackTrace();
        }
    }

    //删
    public void deleteTodayWeather(String cityAndDate)
    {
        try{
            todayWeatherRepository.deleteByCityDate(cityAndDate);
        }
        catch(MongoException e)
        {
            e.printStackTrace();
        }
    }


    //改/更新
    public void updateTodayWeather(TodayWeather todayweather)
    {
        try{
            todayWeatherRepository.save(todayweather);
        }
        catch(MongoException e)
        {
            e.printStackTrace();
        }
    }


}
