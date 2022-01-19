package com.todolist.butan.controller;

import com.todolist.butan.dto.CategoryDto;
import com.todolist.butan.request.CategoryCreateRequest;
import com.todolist.butan.request.CategoryUpdateRequest;
import com.todolist.butan.responce.CategoryResponse;
import com.todolist.butan.service.CategoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        CategoryDto categoryDto = categoryService.createCategory(dozerBeanMapper.map(categoryCreateRequest, CategoryDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(categoryDto, CategoryResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        List<CategoryResponse> categoryResponseList = categoryDtoList.stream().map(categoryDto ->
                dozerBeanMapper.map(categoryDto, CategoryResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(categoryResponseList);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {

        categoryService.deleteCategory(id);

    }

    @PutMapping
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        CategoryDto categoryDto = categoryService.updateCategory(dozerBeanMapper.map(categoryUpdateRequest, CategoryDto.class));
        return ResponseEntity.ok(dozerBeanMapper.map(categoryDto,CategoryResponse.class));
    }
}
