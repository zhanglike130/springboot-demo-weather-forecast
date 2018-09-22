package com.zhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/*
   最近的天气
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    //一般会有5个相同的，所以，不能作为@Id，只能作为寻找的关键字
    private String cityDate;

    private String city;
    private String date;

    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private String aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
}
