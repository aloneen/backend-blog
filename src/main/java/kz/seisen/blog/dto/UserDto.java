package kz.seisen.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;

    private List<PostDto> postsDto;
    private List<CommentDto> commentsDto;
    private List<LikeDto> likesDto;
}
