package com.todolist.butan.service;

import com.todolist.butan.dbmodel.Task;
import com.todolist.butan.dto.CategoryDto;
import com.todolist.butan.dto.TaskDto;
import com.todolist.butan.exception.TaskNotFoundException;
import com.todolist.butan.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final CategoryService categoryService;

    public TaskDto createTask(TaskDto taskDto) {

        taskDto.setCompleted(false);

        CategoryDto categoryDto = categoryService.createCategory(taskDto.getCategory());

        taskDto.setCategory(categoryDto);

        Task task= dozerBeanMapper.map(taskDto, Task.class);

        Task createdTask = taskRepository.save(task);

        return dozerBeanMapper.map(createdTask, TaskDto.class);

    }

    public List<TaskDto> getAllTask() {

        List<Task> taskList = taskRepository.findAll();

        return taskList.stream().map(task ->
                dozerBeanMapper.map(task, TaskDto.class)).collect(Collectors.toList());
    }

    public void deleteTask(Integer id) {

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not Found");
        }
        taskRepository.deleteById(id);
    }

    public TaskDto updateTask(TaskDto taskDto) {

        if(!taskRepository.existsById(taskDto.getId())){
            throw new TaskNotFoundException("Task Not Found!");
        }

        Task task = taskRepository.save(dozerBeanMapper.map(taskDto, Task.class));
        return dozerBeanMapper.map(task,TaskDto.class);


    }
}
