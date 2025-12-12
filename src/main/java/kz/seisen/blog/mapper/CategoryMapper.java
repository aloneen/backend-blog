package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.CategoryDto;
import kz.seisen.blog.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto dto);

    CategoryDto toDto(Category entity);

    List<CategoryDto> toDtoList(List<Category> entities);

}
