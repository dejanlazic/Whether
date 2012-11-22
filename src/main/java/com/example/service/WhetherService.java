package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.City;

public interface WhetherService {
   public List<Map<String, String>> retrieveWeatherData(String city, String days);
   public List<Map<String, String>> retrieveGeoData(String city);
   public void updateStatistics(String city);
   public List<City> retrieveStatistics();
}