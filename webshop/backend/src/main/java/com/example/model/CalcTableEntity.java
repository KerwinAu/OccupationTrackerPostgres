package com.example.model;

import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.ColumnResult;

@Entity
@NamedNativeQuery(
    name = "CalculateAverageOccupation",
    query = "SELECT * FROM CalculateAverageOccupation(:selectedDay, :selectedTimeOfDay) " +
            "ORDER BY " +
            "CASE DayOfWeek " +
            "    WHEN 'Monday' THEN 1 " +
            "    WHEN 'Tuesday' THEN 2 " +
            "    WHEN 'Wednesday' THEN 3 " +
            "    WHEN 'Thursday' THEN 4 " +
            "    WHEN 'Friday' THEN 5 " +
            "    WHEN 'Saturday' THEN 6 " +
            "    WHEN 'Sunday' THEN 7 " +
            "END, " +
            "CASE TimeOfDay " +
            "    WHEN 'Morning' THEN 1 " +
            "    WHEN 'Afternoon' THEN 2 " +
            "    WHEN 'Evening' THEN 3 " +
            "END;",
    resultSetMapping = "map_CalculateAverageOccupation"
)


@SqlResultSetMapping(
    name = "map_CalculateAverageOccupation",
    classes = @ConstructorResult(
        targetClass = CalcTableEntity.class,
        columns = {
            @ColumnResult(type = String.class, name = "dayOfWeek"),
            @ColumnResult(type = String.class, name = "TimeOfDay"),
            @ColumnResult(type = Integer.class, name = "DayCount"),
            @ColumnResult(type = Long.class, name = "Avg_CountCheckedInCustomer"),
            @ColumnResult(type = Long.class, name = "Avg_OccupationRatio")
        }
    )
)
public class CalcTableEntity {
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String dayOfWeek;  
    private int dayCount;      
    private String timeOfDay;  
    private Long avgCountCheckedInCustomer;  
    private Long avgOccupationRatio;  

    public CalcTableEntity(String dayOfWeek, String timeOfDay, Integer dayCount, Long avgCountCheckedInCustomer, Long avgOccupationRatio) {
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this .dayCount = dayCount != null ? dayCount : 0;
        this.avgCountCheckedInCustomer = avgCountCheckedInCustomer != null ? avgCountCheckedInCustomer : 0;
        this.avgOccupationRatio = (avgOccupationRatio != null) ? avgOccupationRatio : 0L;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public long getAvgCountCheckedInCustomer() {
        return avgCountCheckedInCustomer;
    }

    public void setAvgCountCheckedInCustomer(long avgCountCheckedInCustomer) {
        this.avgCountCheckedInCustomer = avgCountCheckedInCustomer;
    }

    public long getAvgOccupationRatio() {
        return avgOccupationRatio;
    }

    public void setAvgOccupationRatio(long avgOccupationRatio) {
        this.avgOccupationRatio = avgOccupationRatio;
    }
}
