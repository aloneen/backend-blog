package kz.seisen.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<PostDto> posts;
    private List<CommentDto> comments;
    private List<LikeDto> likes;
}
