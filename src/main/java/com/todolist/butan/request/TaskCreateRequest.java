package com.todolist.butan.request;


import com.todolist.butan.dbmodel.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequest {

    private String name;

    private CategoryCreateRequest category;

}
