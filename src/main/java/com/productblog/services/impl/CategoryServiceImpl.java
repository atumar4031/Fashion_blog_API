package com.productblog.services.impl;

import com.productblog.dtos.CategoryDto;
import com.productblog.exception.CategoryNotFound;
import com.productblog.models.Category;
import com.productblog.repositories.CategoryRepository;
import com.productblog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Optional.ofNullable(categoryDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("Category name cannot be null"));

        Category category = Category.builder()
                .name(categoryDto.getName())
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();

       Category createdCategory =  categoryRepository.save(category);
       return CategoryDto.builder()
               .name(createdCategory.getName())
               .build();
    }

    @Override
    public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFound("category not found"));

        category.setName(categoryDto.getName());
        Category updatedCategory =  categoryRepository.save(category);
        return CategoryDto.builder()
                .name(updatedCategory.getName())
                .build();
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFound("category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> fetchAllCategories() {
        List<CategoryDto> categoryDtos =  new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category: categories)
            categoryDtos.add(
                    CategoryDto.builder()
                    .name(category.getName())
                    .build());

        return categoryDtos;
    }

    @Override
    public CategoryDto findCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFound("category not found"));
        return  CategoryDto.builder()
                .name(category.getName())
                .build();
    }
}
