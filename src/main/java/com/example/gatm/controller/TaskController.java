package com.example.gatm.controller;

import com.example.gatm.dto.countType;
import com.example.gatm.model.Task;
import com.example.gatm.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/task/vData/percentCountType")
    public List<countType> getPercentageGroupedByType(){

        return taskService.getPercentageGroupedByType();
    }

    //Get tasks
    @GetMapping("/task")
    public List<Task> getTask(){

        return taskService.getTasks();
    }

    //Post a new task
    @PostMapping("/task")
    public  Task addTask(@RequestBody Task task){

        return taskService.save(task);
    }

    //Get task by id
    @GetMapping("/task/{id}")
    public  Task getById(@PathVariable Long id){

        return taskService.getTaskById(id).orElseThrow(() ->new EntityNotFoundException("Requested task not found"));
    }

    //Update task
    @PutMapping("/task/{id}")
    public  ResponseEntity<?> addTask(@RequestBody Task taskPara, @PathVariable Long id){

        if (taskService.existsById(id)){
            Task task = taskService.getTaskById(id).orElseThrow(() ->new EntityNotFoundException("Requested task not found"));
            task.setTitle(taskPara.getTitle());
            task.setDescription(taskPara.getDescription());
            task.setType(taskPara.getType());
            task.setDueDate(taskPara.getDueDate());

            taskService.save(task);
            return ResponseEntity.ok().body(task);
        }else {
            HashMap <String, String>message= new HashMap<>();
            message.put("message", id + "task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("task/{id}") ResponseEntity<?> deleteTask(@PathVariable Long id){
        if (taskService.existsById(id)){
            taskService.delete(id);

            HashMap <String, String>message= new HashMap<>();
            message.put("message", id + "task has been removed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }else {
            HashMap <String, String>message= new HashMap<>();
            message.put("message", id + "task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }


}
