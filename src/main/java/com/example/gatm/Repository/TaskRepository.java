package com.example.gatm.Repository;

import com.example.gatm.dto.countType;
import com.example.gatm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(value = "Select * from tasks.task order by due_date desc", nativeQuery = true)
    List<Task> getAllTaskDueDateDesc();

    @Query(value="Select new com.example.gatm.dto.countType(COUNT(*)/(Select COUNT(*) from Task) *100,type) from Task GROUP BY type")
    List<countType> getPercentageGroupByType();




}
