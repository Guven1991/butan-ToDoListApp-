package com.todolist.butan.service;

import com.todolist.butan.dbmodel.Category;
import com.todolist.butan.dto.CategoryDto;
import com.todolist.butan.exception.CategoryNotFoundException;
import com.todolist.butan.exception.TaskNotFoundException;
import com.todolist.butan.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = categoryRepository.save(dozerBeanMapper.map(categoryDto, Category.class));

        return dozerBeanMapper.map(categoryRepository.save(category), CategoryDto.class);

    }

    public List<CategoryDto> getAllCategory() {
        List<Category> categoryDtoList = categoryRepository.findAll();

        return categoryDtoList.stream().map(category ->
                dozerBeanMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    public void deleteCategory(Integer id){
        if(!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("Category not found");
        }

        categoryRepository.deleteById(id);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto){
        if(!categoryRepository.existsById(categoryDto.getId())){
            throw new CategoryNotFoundException("Task Not Found!");
        }
        Category category = categoryRepository.save(dozerBeanMapper.map(categoryDto,Category.class));
        return dozerBeanMapper.map(category,CategoryDto.class);
    }

    //todo
    public CategoryDto getCategoryByTaskId (Integer taskId){

        return new CategoryDto();
    }
}
