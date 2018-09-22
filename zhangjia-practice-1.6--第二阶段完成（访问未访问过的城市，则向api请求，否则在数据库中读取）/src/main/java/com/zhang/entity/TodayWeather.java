package com.zhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Vector;


/**
 * 今日天气
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayWeather {

    @Id
    private String cityDate;

    private String city;
    private String date;

    private String week;

    private String shidu;
    private String pm25;
    private String pm10;
    private String quality;
    private String wendu;
    private String ganmao;
}
