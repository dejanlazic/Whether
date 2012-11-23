package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.City;

public interface WhetherService {
   public List<Map<String, String>> retrieveWeatherData(String cityName, String days);
   public Map<String, String> retrieveGeoData(String cityName);
   public void updateStatistics(String cityName);
   public List<City> retrieveStatistics();
}