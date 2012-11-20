package com.example.service;

import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.util.JsonParser;

@Service
public class WhetherServiceImpl implements WhetherService {
   public List<Map<String,String>> retrieveData(String city, String days) {
      if(city==null || city.equals("")) return null;
      
      List<Map<String, String>> dataList = null;
      
      // REMOVE START
      // dataList = JsonParser.parse(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(JsonParser.JSON_TEST_RESULT.getBytes()))));
      // if(true) return dataList;
      // REMOVE END
      
      try {
         URL url = new URL(
                  "http://free.worldweatheronline.com/feed/weather.ashx?q=" + city + "&format=json&num_of_days=" + days + "&key=79e067ac37075719121310");
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Accept", "application/json");

         if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }

         dataList = JsonParser.parse(new BufferedReader(new InputStreamReader((conn.getInputStream()))));
         System.out.println("dataList.size(): " + dataList.size());

         conn.disconnect();
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return dataList;
   }   
}