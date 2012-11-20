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
   
   private List<Map<String,String>> dataList = null;

   @RequestMapping("/")
   public String startup(Map<String, Object> map) {
      map.put("city", new City());
      Map<String, String> days = new LinkedHashMap<String, String>();
      days.put("1", "1");
      days.put("2", "2");
      days.put("3", "3");
      days.put("4", "4");
      days.put("5", "5");
      map.put("daysList", days);
      map.put("dataList", dataList);
      
      return "whether";
   }

   @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
   public String retrieveData(@ModelAttribute("city") City city, BindingResult result) {
      dataList = whetherService.retrieveData(city.getName(), city.getDays());
      
      return "redirect:/whether/";
   }
}