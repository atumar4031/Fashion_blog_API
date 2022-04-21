package com.productblog.services.impl;

import com.productblog.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    void updateCategory(long id, CategoryDto categoryDto);
    void deleteCategory(long id);
    List<CategoryDto> fetchPCategories();
    CategoryDto findCategory(long id);
}
