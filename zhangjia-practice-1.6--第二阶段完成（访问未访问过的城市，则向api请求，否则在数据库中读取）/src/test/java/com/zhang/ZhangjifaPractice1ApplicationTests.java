package com.zhang;

import com.zhang.entity.TodayWeather;
import com.zhang.repository.TodayWeatherRepository;
import com.zhang.service.WeatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = { "classpath*:*.xml"})
//@ContextConfiguration ("classpath*:application-context.xml")
public class ZhangjifaPractice1ApplicationTests {

    @Autowired
    private TodayWeatherRepository todayWeatherRepository;

    //@Autowired
    //private WeatherService weatherService;

   @Before
    public void setUp()
    {
        todayWeatherRepository.deleteAll();
    }

    @Test
    public void test1() throws Exception
    {
        System.out.println("test----try");
        todayWeatherRepository.save(new TodayWeather("广州2222","aa","bb","cc","aa","bb","cc","aa","bb","cc"));
        System.out.println("test----next");
        todayWeatherRepository.save(new TodayWeather("上海5522","饶平","bb","cc","aa","bb","cc","aa","bb","cc"));
        todayWeatherRepository.save(new TodayWeather("深圳22","广州","bb","cc","aa","bb","cc","aa","bb","cc"));
        TodayWeather w =  new TodayWeather("aa22","广州433","方法","cc","aa","bb","cc","aa","bb","cc");
        todayWeatherRepository.save(w);
        todayWeatherRepository.save(new TodayWeather("饶平552","饶平","ffc","uii","aa","bb","cc","aa","bb","cc"));
       Assert.assertEquals(5,todayWeatherRepository.findAll().size());

       //这个似乎也是 将所有的 “饶平” 给删除掉的.....
      todayWeatherRepository.deleteByCityDate("饶平552");

      todayWeatherRepository.deleteByCityDate("广州433");
        Assert.assertEquals(3,todayWeatherRepository.findAll().size());
    }

}
