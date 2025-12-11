package kz.seisen.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {
    private String title;
    private String text;
    private List<CategoryDto> categoriesDto;
    private UserDto userDto;
}
