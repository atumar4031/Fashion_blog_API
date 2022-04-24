package com.productblog.services;

import com.productblog.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto CategoryDto);
    CategoryDto updateCategory(long id, CategoryDto CategoryDto);
    List<CategoryDto> fetchAllCategories();
    CategoryDto findCategory(long id);
    void deleteCategory(long id);
}
