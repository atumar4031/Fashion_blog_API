package com.productblog.services.impl;

import com.productblog.dtos.CategoryDto;
import com.productblog.dtos.PostDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    void updateCategory(long id, CategoryDto categoryDto);
    void deleteCategory(long id);
    List<PostDto> fetchPCategories();
    PostDto findCategory(long id);
}
