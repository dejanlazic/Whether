package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class City {  
   @Id
   @GeneratedValue
   private Integer id;
   private String name;
   @Transient
   private String days; // shouldn't be property of this object in reality
   private int counter; // for statistics

   public City() {
      super();
      counter = 0;
   }
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDays() {
      return days;
   }

   public void setDays(String days) {
      this.days = days;
   }

   public int getCounter() {
      return counter;
   }

   public void setCounter(int counter) {
      this.counter = counter;
   }

   @Override
   public String toString() {
      return "City [id=" + id + ", name=" + name + ", days=" + days + ", counter=" + counter + "]";
   }      
}