package kz.seisen.blog.services.impl;

import kz.seisen.blog.dto.CategoryDto;
import kz.seisen.blog.mapper.CategoryMapper;
import kz.seisen.blog.repositories.CategoryRepository;
import kz.seisen.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {



    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public CategoryDto create(CategoryDto commentDto) {
        if (Objects.isNull(commentDto)) return null;

        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(commentDto)));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        CategoryDto old = getById(id);

        if (Objects.isNull(old) || Objects.isNull(categoryDto)) {
            return null;
        }

        old.setName(categoryDto.getName());


        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(old)));
    }

    @Override
    public boolean delete(Long id) {

        categoryRepository.deleteById(id);

        CategoryDto delete = getById(id);

        if (Objects.isNull(delete)) {
            return true;
        }else {
            return false;
        }

    }

}
