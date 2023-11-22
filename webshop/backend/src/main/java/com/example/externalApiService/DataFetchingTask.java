package com.example.externalApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.model.UserEntity;
import com.example.service.UserService;
import com.example.utility.ResponseUtility;

import java.time.LocalDateTime;
import java.time.DayOfWeek;

@Configuration
@EnableScheduling
@Service
public class DataFetchingTask {
    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 1800000) // Fetch and save data every 30 minutes
    public void fetchAndSaveData() {
        int currentHour = LocalDateTime.now().getHour();
        DayOfWeek currentDay = LocalDateTime.now().getDayOfWeek();

        // Check if the current time is within the allowed hours for weekdays and
        // weekends
        if (isWithinWeekdayHours(currentDay, currentHour)
                || isWithinWeekendHours(currentDay, currentHour)) {
            System.out.println("Fetching and saving data");
            // Add this line to print "Fetching and saving data" in the

            // terminal
            String externalData = externalApiService.fetchDataFromExternalApi();
            System.out.println(externalData);

            if (externalData != null) {
                // Save the fetched data in your database
                UserEntity userEntity = new UserEntity();
                System.out.println("Fetched data: "
                        + externalData + userEntity.getId());

                int maxCheckinsAllowed = ResponseUtility.getIntFromJsonResponse(externalData, "maxCheckinsAllowed");

                int countCheckedInCustomer = ResponseUtility.getIntFromJsonResponse(externalData,
                        "countCheckedInCustomer");

                userEntity.setMaxCheckinsAllowed(maxCheckinsAllowed);
                userEntity.setCountCheckedInCustomer(countCheckedInCustomer);
                ResponseUtility.setCurrentDateAndTime(userEntity);
                userService.create(userEntity);
            } else {
                System.out.println("No data fetched"); // Print a message if no data was fetched
            }
        }
    }

    private boolean isWithinWeekdayHours(DayOfWeek day, int hour) {
        // Check if it's a weekday and within 6:00 to 00:00 hours
        return (day != DayOfWeek.SATURDAY
                && day != DayOfWeek.SUNDAY) && (hour >= 6 && hour < 24);
    }

    private boolean isWithinWeekendHours(DayOfWeek day, int hour) {
        // Check if it's a weekend and within 7:00 to 22:00 hours
        return (day == DayOfWeek.SATURDAY
                || day == DayOfWeek.SUNDAY) && (hour >= 7 && hour < 22);
    }
}