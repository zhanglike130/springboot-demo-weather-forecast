package com.zhang.controller;

import com.zhang.ZhangjifaPractice1Application;
import com.zhang.entity.Weather;
import com.zhang.conmon.ReturnWeather;
import com.zhang.entity.TodayWeather;
import com.zhang.service.TodayWeatherService;
import com.zhang.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    //  @Autowired是根据类型进行自动装配的
    @Autowired
    private ReturnWeather deal;

    @Autowired
    private TodayWeatherService todayWeatherService;

    @Autowired
    private WeatherService weatherService;

    //static Integer num = 1;

    @RequestMapping(value="/{city}",method = RequestMethod.GET)
    public String getWeatherInfo(@PathVariable("city")String city,Model model)
    {
        String date="";
        Calendar now= Calendar.getInstance();
        date=date+now.get(Calendar.YEAR)+(now.get(Calendar.MONTH)+1)+now.get(Calendar.DATE);
        // System.out.println("日期为： "+date);

        //若是没有建立表格，这个操作有问题！！   手动建立一个表格，让其存在
        System.out.println("getWeatherInfo中的cityAndDate1: = "+city+date);
        TodayWeather weather = todayWeatherService.getTodayWeather(city+date);
        System.out.println("getWeatherInfo中的cityAndDate2: = "+city+date);
        List<Weather> weatherList = weatherService.getWeatherList(city+date);

        //也就是 在数据库中 没有 该数据
        if( weather == null )
        {
            ReturnWeather deal = new ReturnWeather();

            TodayWeather weatherToday = deal.getTodayWeather(city);

            List <Weather> weatherSet= deal.getWeatherList(city);

            weatherService.addWeatherList(weatherSet);

            if(weatherToday != null)
            {
                model.addAttribute("TodayWth",weatherToday);
                model.addAttribute("WthSet",weatherSet);

                todayWeatherService.addTodayWeather(weatherToday);
            }
            else
            {
                System.out.print("设置error");
                model.addAttribute("error","查询次数过快或城市名称不存在");
            }
        }
        else//如果在数据库中存在，就直接读取数据库中的数据
        {
            System.out.println("从数据库中直接读取的数据: "+city+date+" 的天气");
            model.addAttribute("TodayWth",weather);
            model.addAttribute("WthSet",weatherList);
        }
        //返回weather页面
        return "weather";
    }


}
