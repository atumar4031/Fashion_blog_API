package com.productblog.controllers;

import com.productblog.dtos.CategoryDto;
import com.productblog.dtos.UserDto;
import com.productblog.models.Category;
import com.productblog.services.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value="/blog/api/")
public class CategoryController{

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto){
        categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(
                "category added",
                HttpStatus.OK
        );
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") long id,  @RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(
                "category updated",
                HttpStatus.OK
        );
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") long id){
        CategoryDto categoryDto = categoryService.findCategory(id);
        return new ResponseEntity<>(
                categoryDto,
                HttpStatus.OK
        );
    }

    @GetMapping("/category/all")
    public List<CategoryDto> findCategories(){
        return categoryService.fetchPCategories();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id){

        categoryService.deleteCategory(id);
        return  new ResponseEntity<>("category deletedd",HttpStatus.OK);

    }
}
