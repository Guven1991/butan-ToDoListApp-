package com.todolist.butan.dto;

import com.todolist.butan.dbmodel.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Integer id;
    private String name;
    private boolean isCompleted;
    private CategoryDto category;

}
