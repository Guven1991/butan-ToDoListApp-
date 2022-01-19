package service;

import com.todolist.butan.dbmodel.Task;
import com.todolist.butan.dto.TaskDto;
import com.todolist.butan.exception.TaskNotFoundException;
import com.todolist.butan.repository.TaskRepository;
import com.todolist.butan.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    @Spy
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    private Task task;
    private TaskDto taskDto;

    @Before
public void init(){

        task = Task.builder()
                .id(1)
                .name("walk")
                .isCompleted(false)
                .build();

        taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .isCompleted(task.isCompleted())

                .build();
    }

    @Test
    public void createTask(){
        when(taskRepository.save(any())).thenReturn(task);

        TaskDto taskDtoReturned = taskService.createTask(taskDto);

        assertEquals(Optional.of(1), java.util.Optional.ofNullable(taskDtoReturned.getId()));
        assertEquals("walk",taskDtoReturned.getName());
    }

    @Test
    public void getAllTask(){
        List<Task> taskList =  List.of(task);

        when(taskRepository.findAll()).thenReturn(taskList);

        List<TaskDto> taskDtoList = taskService.getAllTask();

        assertEquals(1,taskDtoList.size());
        assertEquals("walk",taskDtoList.get(0).getName());
    }

    @Test
    public void deleteTask(){
        when(taskRepository.existsById(any())).thenReturn(true);
        taskService.deleteTask(1);
        verify(taskRepository).deleteById(1);
    }

    @Test(expected = TaskNotFoundException.class)
    public void deleteTaskWhenTaskNotFound(){
        when(taskRepository.existsById(any())).thenReturn(false);
        taskService.deleteTask(6);
    }

    @Test
    public void updateTask(){

        task.setName("homework");
        taskDto.setName("homework");


        when(taskRepository.existsById(any())).thenReturn(true);
        when(taskRepository.save(any())).thenReturn(task);
        TaskDto taskDtoReturned = taskService.updateTask(taskDto);
        assertEquals("homework",taskDtoReturned.getName());

    }
}
