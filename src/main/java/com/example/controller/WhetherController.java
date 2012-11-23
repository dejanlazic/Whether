package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.City;
import com.example.service.WhetherService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WhetherController {
   @Autowired
   private WhetherService whetherService;
   
   private List<Map<String, String>> dataList = null;
   private Map<String, String> geoDataMap = null;
   private City city = new City();

   @RequestMapping("/")
   public String startup(Map<String, Object> map) {
      map.put("city", city);
      Map<String, String> days = new LinkedHashMap<String, String>();
      days.put("1", "1");
      days.put("2", "2");
      days.put("3", "3");
      days.put("4", "4");
      days.put("5", "5");
      map.put("daysList", days);
      map.put("dataList", dataList);
      map.put("geoDataMap", geoDataMap);
      
      return "whether";
   }

   @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
   public String retrieveData(@ModelAttribute("city") City city, BindingResult result) {
      this.city = city;
      
      //dataList = whetherService.retrieveWeatherData(city.getName(), city.getDays());
      geoDataMap = whetherService.retrieveGeoData(city.getName());
      
      //whetherService.updateStatistics(city.getName());
      
      return "redirect:/whether/";
   }

   @RequestMapping(value = "/stats", method = RequestMethod.GET)
   public String retrieveStatistics() {
      List<City> cities = whetherService.retrieveStatistics();
      
      // TEST
      for(City c : cities) {
         System.out.println(c);
      }
      
      return "statistics";
   }
}