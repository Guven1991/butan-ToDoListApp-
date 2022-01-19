package com.todolist.butan.controller;

import com.todolist.butan.dto.TaskDto;
import com.todolist.butan.request.TaskCreateRequest;
import com.todolist.butan.request.TaskUpdateRequest;
import com.todolist.butan.responce.TaskResponse;
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
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskCreateRequest taskCreateRequest) {
        TaskDto taskDto = taskService.createTask(dozerBeanMapper.map(taskCreateRequest, TaskDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(taskDto, TaskResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTask() {
        List<TaskDto> taskDtoList = taskService.getAllTask();
        List<TaskResponse> taskResponseList = taskDtoList.stream().map(task ->
                dozerBeanMapper.map(task, TaskResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(taskResponseList);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id) {

        taskService.deleteTask(id);
    }

    @PutMapping
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskUpdateRequest taskUpdateRequest) {

        TaskDto taskDto = taskService.updateTask(dozerBeanMapper.map(taskUpdateRequest, TaskDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(taskDto, TaskResponse.class));

    }
}
