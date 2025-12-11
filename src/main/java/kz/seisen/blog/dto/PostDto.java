package kz.seisen.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String text;
    private Long userId;
    private List<CategoryDto> categories;
    private int likesCount;
}

