package com.productblog.services;

import com.productblog.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto CategoryDto);
    CategoryDto updateCategory(int id, CategoryDto CategoryDto);
    List<CategoryDto> fetchCategory();
    CategoryDto findCategory(int id);
    void deleteCategory(int id);
}
