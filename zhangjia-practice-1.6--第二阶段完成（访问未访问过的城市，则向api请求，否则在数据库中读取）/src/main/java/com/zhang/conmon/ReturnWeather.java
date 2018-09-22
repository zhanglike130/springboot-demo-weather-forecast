package com.zhang.conmon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.entity.Weather;
import com.zhang.entity.TodayWeather;
import com.zhang.repository.TodayWeatherRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;


//@Service注解作用域默认为singleton
@Component
public class ReturnWeather {

    private JsonNode json = null;

    private final String uri = "https://www.sojson.com/open/api/weather/json.shtml?city=";

    private boolean getJsonData(String city)
    {
        System.out.println("getJsonData中的city：="+city);
        //去 服务器 请求一个数据
        RestTemplate rest = new RestTemplate();
        //ResponseEntity<T>是Spring对HTTP请求响应的封装，
        // 包括了几个重要的元素，如响应码、contentType、contentLength、响应消息体等。
        //String.class，表示希望返回的body类型是String
        ResponseEntity<String>  response = rest.getForEntity(uri + city, String.class);

        //查询为成功的状态码
        if (response.getStatusCodeValue() == 200)
        {
            String strBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            try {
                json = mapper.readTree(strBody);
                return true;
            } catch (IOException e) {
                System.out.print("json初始化失败");
                e.printStackTrace();
            }
        }
        return false;
    }

    //用city来获取今天的天气
    public TodayWeather getTodayWeather(String city)
    {
        if (json == null) {
            boolean judge = getJsonData(city);
            if (judge == false)
                return null;
        }
        TodayWeather result = new TodayWeather();
        try {
            JsonNode data = json.get("data");
            result.setShidu(data.get("shidu").toString());
            result.setGanmao(data.get("ganmao").toString());
            result.setPm10(data.get("pm10").toString());
            result.setPm25(data.get("pm25").toString());
            result.setQuality(data.get("quality").toString());
            result.setWendu(data.get("wendu").toString());

            String date="";
            Calendar now= Calendar.getInstance();
            date=date+now.get(Calendar.YEAR)+(now.get(Calendar.MONTH)+1)+now.get(Calendar.DATE);
            System.out.println("日期为： "+date);
            result.setDate(date);
            System.out.println("城市为："+city);
            result.setCity(city);

            String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
            Calendar n=Calendar.getInstance();
            int index= n.get(Calendar.DAY_OF_WEEK)-1;
            result.setWeek(weeks[index]);
            System.out.println("星期为："+result.getWeek());

            result.setCity(city);
            System.out.println("city为："+result.getCity());

            result.setCityDate(city+date);
            System.out.println("cityAndDate为："+result.getCityDate());

        } catch (Exception e) {
            System.out.println("获取当天天气状况失败");
            e.printStackTrace();
            return null;
        }
        return result;
    }


    //用JsonNode作为参数？？
    private Weather returnWth(JsonNode deal,String city) throws Exception
    {
        Weather result=new Weather();

        String date="";
        Calendar now= Calendar.getInstance();
        date=date+now.get(Calendar.YEAR)+(now.get(Calendar.MONTH)+1)+now.get(Calendar.DATE);
        // System.out.println("日期为： "+date);
        result.setDate(date);
        result.setCity(city);

        result.setCityDate(city+date);
       // System.out.println("weather: "+ result.getCityDate());

        result.setAqi(deal.get("aqi").toString());
        result.setDate(deal.get("date").toString());
        result.setFl(deal.get("fl").toString());
        result.setFx(deal.get("fx").toString());
        result.setHigh(deal.get("high").toString());
        result.setLow(deal.get("low").toString());
        result.setNotice(deal.get("notice").toString());
        result.setSunrise(deal.get("sunrise").toString());
        result.setSunset(deal.get("sunset").toString());
        result.setType(deal.get("type").toString());
        return result;
    }

    //通过city获取昨天天气和天气展望
    public Vector<Weather> getWeatherList(String city)
    {
        if(json==null)
        {
            boolean judge = getJsonData(city);
            if(judge==false)
                return null;
        }

        Vector<Weather>  result = new Vector<>();
        try{
            JsonNode yd=json.get("data").get("yesterday");
            Weather yesterday = returnWth(yd,city);
            Weather forecast;
            result.add(yesterday);
            JsonNode fc= json.get("data").get("forecast");
            for(int i=0;i<5;i++)
            {
                //给returnWth的fc.get(i) 只是 forecast部分
                forecast= returnWth(fc.get(i),city);
                result.add(forecast);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return  result;
    }



}




