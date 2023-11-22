package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.externalApiService.DataFetchingTask;
import com.example.model.CalcTableEntity;
import com.example.model.UserEntity;
import com.example.service.UserService;
import java.util.List;
import java.util.Map;
import com.example.externalApiService.ExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.utility.ResponseUtility;

@RestController
@RequestMapping(value = "/UserEntity")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private DataFetchingTask dataFetchingTask;
    @Autowired
    private ExternalApiService externalApiService;
    // URL: http://localhost:8080/UserEntity
    @PostMapping
    public boolean createUser(@RequestBody UserEntity userEntity) {
        return userService.create(userEntity);
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, we are up and running!";
    }

    // URL: http://localhost:8080/UserEntity/id?id=1
    @GetMapping("/id")
    public UserEntity getById(@RequestParam(value = "id", defaultValue = "1") long id) {
        return userService.getById(id);
    }

    //updates entity with id for exsample @maxCheckinsAllowed 
    @PatchMapping("/id")
    public boolean update(@RequestParam(value = "id") long id, @RequestBody UserEntity userEntity) {
        userEntity.setId(id); // Set the id from the URL to the userEntity object
        return userService.update(id,userEntity);
    }
    

    // URL: http://localhost:8080/UserEntity/delete?id=1
    @DeleteMapping("/{id}")
    public boolean delete(@RequestParam long id) {
        return userService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public boolean deleteAll() {
        return userService.deleteAll();
    }

    @GetMapping("/fetchData")
    public ResponseEntity<String> fetchAndSaveData() {
        dataFetchingTask.fetchAndSaveData(); // Trigger the task
        return new ResponseEntity<>("Data fetching task initiated", HttpStatus.OK);
    }

    // URL: http://localhost:8080/UserEntity/getOccupiedRecords?selectedMonth=November
    @GetMapping("/getOccupiedRecords")
    public List<UserEntity> getOccupiedRecords(@RequestParam("selectedMonth") String selectedMonth) {
        return userService.getOccupiedRecords(selectedMonth);
    }

    // URL: http://localhost:8080/UserEntity/getCalcTable?selectedDay=Monday&selectedTimeOfDay=Morning
    @GetMapping("/getCalcTable")
    public List<CalcTableEntity> getCalcTable(@RequestParam(required = false) String selectedDay,
            @RequestParam(required = false) String selectedTimeOfDay) {
        return userService.getCalcTable(selectedDay, selectedTimeOfDay);
    }

    // URL: http://localhost:8080/UserEntity/getLowestOccupation
    @GetMapping("/getLowestOccupation")
    public List<UserEntity> getLowestOccupation() {
        return userService.getLowestOccupation();
    }

    // URL: http://localhost:8080/UserEntity/getHighestOccupation
    @GetMapping("/getHighestOccupation")
    public List<UserEntity> getHighestOccupation() {
        return userService.getHighestOccupation();
    }

    // URL: http://localhost:8080/UserEntity/getWeekdaysView
    @GetMapping("/getWeekdaysView")
    public List<UserEntity> getWeekdaysView() {
        return userService.getWeekdaysView();
    }

    // URL: http://localhost:8080/UserEntity/getWeekdaysView
    @GetMapping("/getWeekenddaysView")
    public List<UserEntity> getWeekenddaysView() {
        return userService.getWeekenddaysView();
    }
    // Url: http://localhost:8080/UserEntity/getCurrentStatus
    @GetMapping("/getCurrentStatus")
    public Map<?,?> getCurrentStatus() {
            String externalData = externalApiService.fetchDataFromExternalApi();
            System.out.println(externalData);
            Map<?,?> latest = null;
            if (externalData != null) {
                // Save the fetched data in your database
                UserEntity userEntity = new UserEntity();
                System.out.println("Fetched data: "
                        + externalData + userEntity.getId());

                int maxCheckinsAllowed = ResponseUtility.getIntFromJsonResponse(externalData, "maxCheckinsAllowed");

                int countCheckedInCustomer = ResponseUtility.getIntFromJsonResponse(externalData,
                        "countCheckedInCustomer");
                         latest = Map.of("maxCheckinsAllowed", maxCheckinsAllowed, "countCheckedInCustomer", countCheckedInCustomer);
                    }
                 return latest;
                }
                    
}




