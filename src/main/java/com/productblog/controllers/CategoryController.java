package com.productblog.controllers;

import com.productblog.dtos.CategoryDto;
import com.productblog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/blog/api/category")
public class CategoryController{

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/update/{id}")
    public CategoryDto updateCategory(@PathVariable("id") long id,  @RequestBody CategoryDto categoryDto){
        return  categoryService.updateCategory(id, categoryDto);
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") long id){
        return categoryService.findCategory(id);
    }

    @GetMapping("/all")
    public List<CategoryDto> findCategories(){
        return categoryService.fetchAllCategories();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id){

        categoryService.deleteCategory(id);
        return  new ResponseEntity<>("category deletedd",HttpStatus.OK);

    }
}
