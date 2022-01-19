package service;

import com.todolist.butan.dbmodel.Category;
import com.todolist.butan.dto.CategoryDto;
import com.todolist.butan.exception.CategoryNotFoundException;
import com.todolist.butan.repository.CategoryRepository;
import com.todolist.butan.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    @Spy
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;
    private CategoryDto categoryDto;

    @Before
    public void init() {

        category = Category.builder()
                .id(1)
                .name("software")
                .build();

        categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Test
    public void createCategory() {

        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDto categoryDtoReturned = categoryService.createCategory(categoryDto);
        assertEquals(java.util.Optional.of(1), Optional.ofNullable(categoryDtoReturned.getId()));
        assertEquals("software",categoryDtoReturned.getName());

    }

    @Test
    public void getAllCategory(){

      List<Category> categoryList = List.of(category);
      when(categoryRepository.findAll()).thenReturn(categoryList);
      List<CategoryDto> categoryDtoList = categoryService.getAllCategory();

      assertEquals(1,categoryDtoList.size());
      assertEquals(Optional.of(1), Optional.ofNullable(categoryDtoList.get(0).getId()));

    }

    @Test
    public void deleteCategory(){
        when(categoryRepository.existsById(any())).thenReturn(true);
        categoryService.deleteCategory(1);
        verify(categoryRepository).deleteById(1);
    }

    @Test(expected = CategoryNotFoundException.class)
    public void deleteTaskWhenTaskNotFound(){
        when(categoryRepository.existsById(any())).thenReturn(false);
        categoryService.deleteCategory(6);
    }

    @Test
    public void updateCategory(){

        category.setName("shop");
        categoryDto.setName("shop");


        when(categoryRepository.existsById(any())).thenReturn(true);
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDto categoryDtoReturned = categoryService.updateCategory(categoryDto);
        assertEquals("shop",categoryDtoReturned.getName());

    }

}
