package com.example.gatm.Repository;

import com.example.gatm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(value = "Select * from task order by due_date desc", nativeQuery = true)
    List<Task> getAllTaskDueDateDesc();

}
