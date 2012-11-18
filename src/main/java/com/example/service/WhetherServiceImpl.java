package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.example.model.City;

@Service
public class WhetherServiceImpl implements WhetherService {
   public String retrieveData(City city) {
      getWeatherData();
      return city.getName();
   }

   private void getWeatherData() {
      try {
         URL url = new URL(
                  "http://free.worldweatheronline.com/feed/weather.ashx?q=Zagreb&format=json&num_of_days=2&key=79e067ac37075719121310");
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Accept", "application/json");

         if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }

         BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
         String output;
         System.out.println("Output from Server .... \n");
         while ((output = br.readLine()) != null) {
            System.out.println(output);
         }

         conn.disconnect();
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
