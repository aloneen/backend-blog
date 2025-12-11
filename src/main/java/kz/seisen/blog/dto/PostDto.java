package kz.seisen.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String text;
    private UserDto userDto;
    private List<CommentDto> commentsDto;
    private List<CategoryDto> categoriesDto;
    private List<LikeDto> likesDto;
}

