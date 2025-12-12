package kz.seisen.blog.services;



import kz.seisen.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto update(Long id, CategoryDto categoryDto);
    boolean delete(Long id);

}
