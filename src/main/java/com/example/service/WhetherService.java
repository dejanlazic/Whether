package com.example.service;

import java.util.List;
import java.util.Map;

public interface WhetherService {
   public List<Map<String,String>> retrieveData(String city, String days);
}