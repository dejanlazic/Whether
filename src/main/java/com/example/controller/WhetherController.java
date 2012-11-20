package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.City;
import com.example.service.WhetherService;

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
      map.put("dataList", dataList);
      
      return "whether";
   }

   @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
   public String retrieveData(@ModelAttribute("city") City city, BindingResult result) {
      System.out.println(whetherService.retrieveData(city.getName()));
      dataList = whetherService.retrieveData(city.getName());
      
      return "redirect:/whether/";
   }
}