package com.example.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.model.CalcTableEntity;
import com.example.model.UserEntity;
import com.example.repository.UserEntityRepository;
import com.example.service.UserService;
import java.util.Collections;
@Service
public class UserServiceImplementation implements UserService {
    
    @Autowired private UserEntityRepository userEntityRepository;


    @Override
    public boolean create(UserEntity user) {
        // Implement the logic to create a user here
        // INSERT INTO dbo.keauit00_custom_user_table(id, firstname ... )
        userEntityRepository.save(user);
        return true;
    }

    @Override
    public UserEntity getById(long id) {
        // Implement the logic to get a user by ID here
        // You can use userEntityRepository or any other method to retrieve the user
        return userEntityRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> getAll() {
        // Implement the logic to get all users here
        // You can use userEntityRepository or any other method to retrieve all users
        return userEntityRepository.findAll();
    }

    
    @Override
    public boolean update(long id, UserEntity user) {
        UserEntity existingUser = userEntityRepository.findById(id).orElse(null);

        if (existingUser != null) {
            if (user.getMaxCheckinsAllowed() != null) {
                existingUser.setMaxCheckinsAllowed(user.getMaxCheckinsAllowed());
            }
            
            if (user.getCountCheckedInCustomer() != null) {
                existingUser.setCountCheckedInCustomer(user.getCountCheckedInCustomer());
            }
            if (user.getMonth() != null) {
                existingUser.setMonth(user.getMonth());
            }
            if(user.getDay() != null){
                existingUser.setDay(user.getDay());
            }
 
            userEntityRepository.save(existingUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        // Implement the logic to delete a user by ID here
        // Return true if the user was successfully deleted, or false otherwise
        userEntityRepository.deleteById(id);
        return true;
    }
    
    @Override
    public boolean deleteAll() {
        try {
            userEntityRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void saveUser(UserEntity user) {
        userEntityRepository.save(user);
    }
    
    @Override
    public List<UserEntity> getOccupiedRecords(String selectedMonth) {
        return userEntityRepository.getOccupiedRecords(selectedMonth);
    }

    @Override
    public List<CalcTableEntity> getCalcTable(String selectedDay, String selectedTimeOfDay){
        return userEntityRepository.getCalcTable(selectedDay, selectedTimeOfDay);
    }

    @Override
    public List<UserEntity> getLowestOccupation(){
        return userEntityRepository.getLowestOccupation();
    }

    @Override
    public List<UserEntity> getHighestOccupation(){
        return userEntityRepository.getHighestOccupation();
    }

    @Override
    public List<UserEntity> getWeekdaysView(){
        return userEntityRepository.getWeekdaysView();
    }

    @Override
    public List<UserEntity> getWeekenddaysView(){
        return userEntityRepository.getWeekenddaysView();
    }


}
