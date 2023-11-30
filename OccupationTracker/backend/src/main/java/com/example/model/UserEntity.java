package com.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "keauit00_occupied")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    private Integer maxCheckinsAllowed;
    @Column
    private Integer countCheckedInCustomer;

    @Column
    private String month; 
    @Column
    private String day; 

    @Column
    private LocalTime time;

    @Column
    private LocalDate localDate; 

    @Column
    private String dayType; 

    @Column
    private String timeOfDay; 

    @Column
    private Double occupationRatio;
    
    // Getter and Setter 

    public Double getOccupationRatio() {
        return occupationRatio;
    }

    public void setOccupationRatio(Double occupationRatio) {
        this.occupationRatio = occupationRatio;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }
    
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getMaxCheckinsAllowed() {
        return maxCheckinsAllowed;
    }

    public void setMaxCheckinsAllowed(Integer maxCheckinsAllowed) {
        this.maxCheckinsAllowed = maxCheckinsAllowed;
    }

    public Integer getCountCheckedInCustomer() {
        return countCheckedInCustomer;
    }

    public void setCountCheckedInCustomer(Integer countCheckedInCustomer) {
        this.countCheckedInCustomer = countCheckedInCustomer;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }
}
