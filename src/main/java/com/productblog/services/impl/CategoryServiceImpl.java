package com.productblog.services.impl;

import com.productblog.dtos.CategoryDto;
import com.productblog.dtos.PostDto;
import com.productblog.models.Category;
import com.productblog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {

        Optional.ofNullable(categoryDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("Category name cannot be null"));

        Category category = Category.builder()
                .name(categoryDto.getName())
                .created_at(LocalDateTime.now())
                .modify_at(categoryDto.getModify_at())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(long id, CategoryDto categoryDto) {

    }

    @Override
    public void deleteCategory(long id) {

    }

    @Override
    public List<PostDto> fetchPCategories() {
        return null;
    }

    @Override
    public PostDto findCategory(long id) {
        return null;
    }
}
