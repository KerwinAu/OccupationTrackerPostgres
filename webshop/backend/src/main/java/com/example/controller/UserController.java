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

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.api.GlobalOpenTelemetry;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*")
@RestController
// @CrossOrigin(origins = "http://localhost:4200")
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
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("createUser").startSpan();
    try (Scope scope = span.makeCurrent()) {

        return userService.create(userEntity);
    }finally{
        span.end();
    }}

    @GetMapping("/")
public List<UserEntity> getAll() {

    Span span = GlobalOpenTelemetry.getTracer("Occupation").spanBuilder("getAll").startSpan();
    try (Scope scope = span.makeCurrent()) {

        return userService.getAll();

    } finally {
        span.end();
    }
}
    


    //@GetMapping("/")
    //public String hello() {
    //    return "Hello, we are up and running!";
    //}



    // URL: http://localhost:8080/UserEntity/id?id=1
    @GetMapping("/id")
    public UserEntity getById(@RequestParam(value = "id", defaultValue = "1") long id) {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getCalcTable").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getById(id);
    }finally{
        span.end();
    }}

    //updates entity with id for exsample @maxCheckinsAllowed 
    @PatchMapping("/id")
    public boolean update(@RequestParam(value = "id") long id, @RequestBody UserEntity userEntity) {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getById").startSpan();
    try (Scope scope = span.makeCurrent()) {
        userEntity.setId(id); // Set the id from the URL to the userEntity object
        return userService.update(id,userEntity);
    }finally{
        span.end();
    }}
    

    // URL: http://localhost:8080/UserEntity/delete?id=1
    @DeleteMapping("/{id}")
    public boolean delete(@RequestParam long id) {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("deleteById").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.delete(id);
    }finally{
        span.end();
    }}

    @DeleteMapping("/deleteAll")
    public boolean deleteAll() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("deleteAll").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.deleteAll();
    }finally{
        span.end();
    }}

    @GetMapping("/fetchData")
    public ResponseEntity<String> fetchAndSaveData() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("fetchData").startSpan();
    try (Scope scope = span.makeCurrent()) {
        dataFetchingTask.fetchAndSaveData(); // Trigger the task
        return new ResponseEntity<>("Data fetching task initiated", HttpStatus.OK);
    }finally{
        span.end();
    }}

    // URL: http://localhost:8080/UserEntity/getOccupiedRecords?selectedMonth=November
    @GetMapping("/getOccupiedRecords")
    public List<UserEntity> getOccupiedRecords(@RequestParam("selectedMonth") String selectedMonth) {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getOccupiedReccords").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getOccupiedRecords(selectedMonth);
    }finally{
        span.end();
    }}

    // URL: http://localhost:8080/UserEntity/getCalcTable?selectedDay=Monday&selectedTimeOfDay=Morning
    @GetMapping("/getCalcTable")
    public List<CalcTableEntity> getCalcTable(@RequestParam(required = false) String selectedDay,
            @RequestParam(required = false) String selectedTimeOfDay) {
                
                    Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getCalcTable").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getCalcTable(selectedDay, selectedTimeOfDay);
            } finally {
        span.end();
    }

    }

    // URL: http://localhost:8080/UserEntity/getLowestOccupation
    @GetMapping("/getLowestOccupation")
    public List<UserEntity> getLowestOccupation() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getLowestOccupation").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getLowestOccupation();
    }finally{span.end();}}

    // URL: http://localhost:8080/UserEntity/getHighestOccupation
    @GetMapping("/getHighestOccupation")
    public List<UserEntity> getHighestOccupation() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getHighestOccupation").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getHighestOccupation();
    }finally{ span.end();}}

    // URL: http://localhost:8080/UserEntity/getWeekdaysView
    @GetMapping("/getWeekdaysView")
    public List<UserEntity> getWeekdaysView() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getWeekdaysView").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getWeekdaysView();
    }finally{span.end();}}

    // URL: http://localhost:8080/UserEntity/getWeekdaysView
    @GetMapping("/getWeekenddaysView")
    public List<UserEntity> getWeekenddaysView() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getWeekenddaysView").startSpan();
    try (Scope scope = span.makeCurrent()) {
        return userService.getWeekenddaysView();
    }finally{span.end();}}
    // Url: http://localhost:8080/UserEntity/getCurrentStatus
    @GetMapping("/getCurrentStatus")
    public Map<?,?> getCurrentStatus() {
        Span span = GlobalOpenTelemetry.getTracer("week").spanBuilder("getCurrentStatus").startSpan();
    try (Scope scope = span.makeCurrent()) {
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
                }finally{span.end();
                }}
                    
}




