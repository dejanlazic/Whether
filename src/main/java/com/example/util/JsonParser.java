package com.example.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
   public static final String JSON_WEATHER_DATA = "{\"data\":{" 
            + "\"current_condition\":[{\"cloudcover\":\"100\",\"humidity\":\"93\",\"observation_time\":\"06:23 PM\",\"precipMM\":\"0.0\",\"pressure\":\"1016\",\"temp_C\": \"9\",\"temp_F\": \"48\",\"visibility\":\"4\",\"weatherCode\": \"143\",\"weatherDesc\":[{\"value\":\"Mist\"}],\"winddir16Point\":\"N\",\"winddirDegree\":\"0\",\"windspeedKmph\":\"0\",\"windspeedMiles\":\"0\"}],"
            + "\"weather\":[{\"date\":\"2012-11-18\",\"tempMaxC\":\"11\",\"tempMaxF\":\"51\",\"tempMinC\":\"6\",\"tempMinF\":\"42\",\"weatherDesc\":[{\"value\":\"Overcast\"}],\"windspeedKmph\":\"5\",\"windspeedMiles\":\"3\"}"
            + ",{\"date\":\"2012-11-19\",\"tempMaxC\":\"10\",\"tempMaxF\":\"50\",\"tempMinC\":\"4\",\"tempMinF\":\"39\",\"weatherDesc\":[{\"value\":\"Partly Cloudy\"}],\"windspeedKmph\":\"12\",\"windspeedMiles\":\"7\"}]"
            + "}}";
   
   public static final String JSON_GEO_DATA = "{\"geonames\":[{\"countryName\":\"Croatia\",\"countryCode\":\"HR\",\"lng\":15.9779834747314,\"fcodeName\":\"capital of a political entity\",\"lat\":45.8144436673781,\"population\":698966}]}";
   
   public static List<Map<String,String>> parseWeatherData(Reader reader) {
      List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
      
      JSONParser parser = new JSONParser();      
      try {
         JSONObject jsonObject = (JSONObject) parser.parse(reader);
         // System.out.println(jsonObject);

         JSONObject jsonData = (JSONObject) jsonObject.get("data");
         // System.out.println(jsonData);

         // current condition

         JSONArray jsonCurrConditionArr = (JSONArray) jsonData.get("current_condition");
         // System.out.println(jsonCurrConditionArr);

         JSONObject jsonCurrCondition = (JSONObject) jsonCurrConditionArr.get(0);
         // System.out.println(jsonCurrCondition);

         Map<String, String> currentConditionData = new HashMap<String, String>();
         currentConditionData.put("pressure", (String) jsonCurrCondition.get("pressure"));
         currentConditionData.put("tempC", (String) jsonCurrCondition.get("temp_C"));
         currentConditionData.put("tempF", (String) jsonCurrCondition.get("temp_F"));
         currentConditionData.put("visibility", (String) jsonCurrCondition.get("visibility"));
         currentConditionData.put("humidity", (String) jsonCurrCondition.get("humidity"));
         JSONArray jsonWeatherDescArr = (JSONArray) jsonCurrCondition.get("weatherDesc");
         JSONObject jsonWeatherDesc = (JSONObject) jsonWeatherDescArr.get(0);
         currentConditionData.put("weatherDesc", (String) jsonWeatherDesc.get("value"));
         
         maps.add(currentConditionData);
         
         for (Entry<String, String> entry : currentConditionData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
         }
         System.out.println("---");

         // weather

         JSONArray jsonWeatherArr = (JSONArray) jsonData.get("weather");
         System.out.println(jsonWeatherArr);

         @SuppressWarnings("unchecked")
         Iterator<JSONObject> iterator = jsonWeatherArr.iterator();
         while (iterator.hasNext()) {
            JSONObject jsonWeather = (JSONObject) iterator.next();
            System.out.println(jsonWeather);

            Map<String, String> weatherData = new HashMap<String, String>();
            weatherData.put("date", (String) jsonWeather.get("date"));
            weatherData.put("tempMinC", (String) jsonWeather.get("tempMinC"));
            weatherData.put("tempMaxC", (String) jsonWeather.get("tempMaxC"));
            weatherData.put("tempMinF", (String) jsonWeather.get("tempMinF"));
            weatherData.put("tempMaxF", (String) jsonWeather.get("tempMaxF"));
            weatherData.put("windspeedKmph", (String) jsonWeather.get("windspeedKmph"));
            weatherData.put("windspeedMiles", (String) jsonWeather.get("windspeedMiles"));
            jsonWeatherDescArr = (JSONArray) jsonWeather.get("weatherDesc");
            jsonWeatherDesc = (JSONObject) jsonWeatherDescArr.get(0);
            weatherData.put("weatherDesc", (String) jsonWeatherDesc.get("value"));

            maps.add(weatherData);
            
            for (Entry<String, String> entry : weatherData.entrySet()) {
               System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("---");
         }
      } catch (IOException e) {
         System.out.println(e);
      } catch (ParseException e) {
         System.out.println(e);
      }
      
      return maps;
   }
   
   public static Map<String,String> parseGeoData(Reader reader) {
      Map<String, String> geoData = new HashMap<String, String>();
      
      JSONParser parser = new JSONParser();      
      try {
         JSONObject jsonObject = (JSONObject) parser.parse(reader);
         System.out.println(jsonObject);

         JSONArray jsonGeonamesArr = (JSONArray) jsonObject.get("geonames");
         System.out.println(jsonGeonamesArr);

         JSONObject jsonGeonames = (JSONObject) jsonGeonamesArr.get(0);
         System.out.println(jsonGeonames);

         geoData.put("countryName", (String) jsonGeonames.get("countryName"));
         geoData.put("countryCode", (String) jsonGeonames.get("countryCode"));
         geoData.put("fcodeName", (String) jsonGeonames.get("fcodeName"));
         geoData.put("longitude", jsonGeonames.get("lng").toString());
         geoData.put("latitude", jsonGeonames.get("lat").toString());
         geoData.put("population", jsonGeonames.get("population").toString());
         
//         for (Entry<String, String> entry : geoData.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//         }
//         System.out.println("---");
      } catch (IOException e) {
         System.out.println(e);
      } catch (ParseException e) {
         System.out.println(e);
      }
      
      return geoData;
   }
}