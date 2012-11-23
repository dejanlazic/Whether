package com.example.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

//import org.geonames.Toponym;
//import org.geonames.ToponymSearchCriteria;
//import org.geonames.ToponymSearchResult;
//import org.geonames.WebService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.City;
import com.example.util.JsonParser;

@Service
public class WhetherServiceImpl implements WhetherService {
   @PersistenceContext
   EntityManager em;

   public List<Map<String, String>> retrieveWeatherData(String cityName, String days) {
      if (cityName == null || cityName.equals(""))
         return null;

      List<Map<String, String>> dataList = null;

      // TEST - START
      // dataList = JsonParser.parseWeatherData(new BufferedReader(new
      // InputStreamReader(new
      // ByteArrayInputStream(JsonParser.JSON_WEATHER_DATA.getBytes()))));
      // if(true) return dataList;
      // TEST - END

      try {
         URL url = new URL("http://free.worldweatheronline.com/feed/weather.ashx?q=" + cityName
                  + "&format=json&num_of_days=" + days + "&key=79e067ac37075719121310");
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Accept", "application/json");

         if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }

         dataList = JsonParser.parseWeatherData(new BufferedReader(new InputStreamReader((conn.getInputStream()))));

         conn.disconnect();
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return dataList;
   }

   public Map<String, String> retrieveGeoData(String cityName) {
      if (cityName == null || cityName.equals(""))
         return null;

      Map<String, String> geoDataMap = null;

      // TEST - START
      geoDataMap = JsonParser.parseGeoData(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
               JsonParser.JSON_GEO_DATA.getBytes()))));
      // if (true) return geoDataMap;
      // TEST - END

      // try {
      // URL url = new URL("http://api.geonames.org/searchJSON?q=" + city +
      // "&maxRows=1&username=delazic");
      // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      // conn.setRequestMethod("GET");
      // conn.setRequestProperty("Accept", "application/json");
      //
      // if (conn.getResponseCode() != 200) {
      // throw new RuntimeException("Failed : HTTP error code : " +
      // conn.getResponseCode());
      // }
      //
      // // TEST: START
      // BufferedReader br = new BufferedReader(new
      // InputStreamReader(conn.getInputStream()));
      // StringBuilder sb = new StringBuilder();
      // String read = br.readLine();
      // while(read != null) {
      // //System.out.println(read);
      // sb.append(read);
      // read = br.readLine();
      // }
      // System.out.println(sb.toString());
      // // TEST: END
      //
      // geoDataList = JsonParser.parse(new BufferedReader(new
      // InputStreamReader((conn.getInputStream()))));
      //
      // conn.disconnect();
      // } catch (MalformedURLException e) {
      // e.printStackTrace();
      // } catch (IOException e) {
      // e.printStackTrace();
      // }

      /* Calling WS using library */
      // WebService.setUserName("delazic");
      // ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
      // searchCriteria.setQ(city);
      // ToponymSearchResult searchResult;
      // try {
      // searchResult = WebService.search(searchCriteria);
      //
      // for (Toponym toponym : searchResult.getToponyms()) {
      // System.out.println(toponym.getName() + ", " + toponym.getCountryName()
      // + ", " + toponym.getLatitude() + ", " + toponym.getLongitude());
      //
      // Map<String, String> geoData = new HashMap<String, String>();
      // geoData.put("name", toponym.getName());
      // geoData.put("country", toponym.getCountryName());
      // geoData.put("latitude", Double.toString(toponym.getLatitude()));
      // geoData.put("longitude", Double.toString(toponym.getLongitude()));
      // dataList.add(geoData);
      // }
      // } catch (Exception e) {
      // System.out.println(e);
      // }

      return geoDataMap;
   }

   @Transactional
   public void updateStatistics(String cityName) {
      Query query = em.createQuery("FROM City c WHERE c.name = :name");
      query.setParameter("name", cityName);
      City city = null;
      try {
         city = (City) query.getSingleResult();
      } catch (NoResultException nre) {
         // Ignore this because as per your logic this is ok
         System.out.println("No data in DB for " + cityName);
      }

      if (city == null) {
         System.out.println("Not found: " + cityName);
         city = new City(cityName);
      } else {
         System.out.println("Found: " + city);         
      }
      
      city.setCounter(city.getCounter() + 1);
      em.persist(city);
   }

   @Transactional
   public List<City> retrieveStatistics() {
      CriteriaQuery<City> c = em.getCriteriaBuilder().createQuery(City.class);
      c.from(City.class);
      // c.orderBy(em.getCriteriaBuilder().asc(from.get("lastName")));
      return em.createQuery(c).getResultList();
   }
}