package com.example.repository;
import com.example.model.CalcTableEntity;
import com.example.model.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "EXEC getMonthlyRecords @selectedMonth = :selectedMonth", nativeQuery = true)
    List<UserEntity> getOccupiedRecords(@Param("selectedMonth") String selectedMonth);
    

    @Query(name = "CalculateAverageOccupation", nativeQuery = true)
    List<CalcTableEntity> getCalcTable(String selectedDay, String selectedTimeOfDay);
    

    @Query(value = "Select * from LowestCheckedInCustomerView", nativeQuery = true)
    List<UserEntity> getLowestOccupation();

  
    @Query(value = "Select * from HighestCheckedInCustomerView", nativeQuery = true)
    List<UserEntity> getHighestOccupation();

    
    @Query(value = "Select * from WeekdaysView", nativeQuery = true)
    List<UserEntity> getWeekdaysView();

    
    @Query(value = "Select * from WeekenddaysView", nativeQuery = true)
    List<UserEntity> getWeekenddaysView();


}
