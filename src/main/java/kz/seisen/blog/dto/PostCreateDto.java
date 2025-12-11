package kz.seisen.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostCreateDto {
    private String title;
    private String text;
    private List<CategoryDto> categoryIds;
    private Long userId;
}
