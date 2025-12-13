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
    private Long userId;
    private String username;
    private List<CommentDto> comments;
    private List<CategoryDto> categories;
    private List<LikeDto> likes;
}

