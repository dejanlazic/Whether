package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.City;
import com.example.service.WhetherService;

import java.util.Map;

@Controller
public class WhetherController {
   @Autowired
   private WhetherService whetherService;

   @RequestMapping("/")
   public String listPeople(Map<String, Object> map) {
      map.put("city", new City());
      
      return "whether";
   }

   @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
   public String retrieveData(@ModelAttribute("city") City city, BindingResult result) {
      System.out.println(whetherService.retrieveData(city));
      return "redirect:/whether/";
   }
}