package com.example.service;
import com.example.model.CalcTableEntity;
import com.example.model.UserEntity;
import java.util.List; // Correct import for List




public interface UserService {
    boolean create(UserEntity user);
    void saveUser(UserEntity user);
    boolean update( long id, UserEntity userEntity);
    boolean delete(long id);
    boolean deleteAll();

    UserEntity getById(long id);

    List<UserEntity>getAll();

    List<UserEntity> getOccupiedRecords(String selectedMonth);
  
    List<CalcTableEntity> getCalcTable(String selectedDay, String selectedTimeOfDay);
    
    List<UserEntity> getLowestOccupation();

    List<UserEntity> getHighestOccupation();

    List<UserEntity> getWeekdaysView();

    List<UserEntity> getWeekenddaysView();


}
