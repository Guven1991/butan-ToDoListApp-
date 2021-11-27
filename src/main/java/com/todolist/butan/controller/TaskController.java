package com.todolist.butan.controller;

import com.todolist.butan.dto.TaskDto;
import com.todolist.butan.request.TaskRequest;
import com.todolist.butan.responce.TaskResponce;
import com.todolist.butan.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.dozer.DozerBeanMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponce> createTask(@RequestBody TaskRequest taskRequest) {
        TaskDto taskDto = taskService.createTask(dozerBeanMapper.map(taskRequest, TaskDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(taskDto, TaskResponce.class));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponce>> getAllTask() {
        List<TaskDto> taskDtoList = taskService.getAllTask();
        List<TaskResponce> taskResponceList = taskDtoList.stream().map(task ->
                dozerBeanMapper.map(task, TaskResponce.class)).collect(Collectors.toList());
        return ResponseEntity.ok(taskResponceList);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id) {

        taskService.deleteTask(id);
    }

    @PutMapping
    public ResponseEntity<TaskResponce> updateTask(@RequestBody TaskRequest taskRequest) {

        TaskDto taskDto = taskService.updateTask(dozerBeanMapper.map(taskRequest, TaskDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(taskDto,TaskResponce.class));

    }
}
