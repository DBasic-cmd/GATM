package com.example.gatm.service;

import com.example.gatm.Repository.TaskRepository;
import com.example.gatm.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task>getTasks(){
        return taskRepository.getAllTaskDueDateDesc();
    }


    public Task save(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id){
        return taskRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
