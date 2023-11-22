package com.example.utility;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import com.example.model.UserEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ResponseUtility {
    public static int getIntFromJsonResponse(String responseString, String key) {
        int value = 0;
        try {
            int jsonStart = responseString.indexOf('{');
            int jsonEnd = responseString.lastIndexOf('}');
            String jsonResponse = responseString.substring(jsonStart, jsonEnd + 1);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            value = jsonNode.get(key).asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setCurrentDateAndTime(UserEntity userEntity) {
        try {
            Instant currentInstant = Instant.now();
            ZoneId zoneId = ZoneId.of("CET");
            ZonedDateTime currentZonedDateTime = currentInstant.atZone(zoneId);
            LocalDateTime currentLocalDateTime = currentZonedDateTime.toLocalDateTime();

            Month month = currentLocalDateTime.getMonth();
            String monthInWords = month.toString();

            DayOfWeek day = currentLocalDateTime.getDayOfWeek();
            String dayInWords = day.toString();

            LocalTime time = currentLocalDateTime.toLocalTime();
            LocalDate localDate = currentLocalDateTime.toLocalDate();

            userEntity.setMonth(monthInWords);
            userEntity.setDay(dayInWords);
            userEntity.setTime(time);
            userEntity.setLocalDate(localDate);

            // Calculate the week number and set it as an integer
            // WeekFields weekFields = WeekFields.of(Locale.getDefault());
            // int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
            // userEntity.setWeek(weekNumber);

            // Calculate time of day (morning, afternoon, or evening)
            String timeOfDay = getTimeOfDay(time);
            // Set the time of day in the UserEntity
            userEntity.setTimeOfDay(timeOfDay);
            String dayType = isWeekendDay(day);
            userEntity.setDayType(dayType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String isWeekendDay(DayOfWeek dayOfWeek) {
        // Check if the dayOfWeek is Saturday or Sunday
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return "WEEKEND";
        } else {
            return "WEEKDAY";
        }
    }

    private static String getTimeOfDay(LocalTime time) {
    if (time.isBefore(LocalTime.NOON)) {
        return "morning";
    } else if (time.isBefore(LocalTime.of(17, 0))) {
        return "afternoon";
    } else {
        return "evening";
    }
}


}
